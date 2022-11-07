package nsf.ecap.MonitorPlan_Suite;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
//

public class CreateActiveMonitorPlan extends TestSuiteBase_MonitorPlan{
	
	public boolean IsBrowserPresentAlready = false;
	public String ClientId1="C0227462";	
	public String FacilityFieldText;
	public String Monitor_ID;
	public String WorkOrderId;
	
	
	public String AnnList_BillCode_PriceValue;
	public String ProSrvc_FirstBillCode_PriceValue;
	public String ProSrvc_SecondBillCode_PriceValue;
	public String ProSrvc_ThirdBillCode_PriceValue;
	public String ProSrvc_FourthBillCode_PriceValue;
	public String ProSrvc_FifthBillCode_PriceValue;
	public String ProSrvc_SixthBillCode_PriceValue;
	
	
	public Integer ANNLIST_TaskTableNo;
	public Integer PROFSVC_TaskTableNo;
	public Integer iteration;
	public Integer WOTaskCount;
	
	public boolean Default_DD_Value_Check;// = true;
	public String Delete_Bttn_Xpath = null;

	
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Throwable {
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
		
		
		Facility_ID = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Facility_LookUp").trim();
		/*Testing_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Testing_Task_Details");
		TaskTab_TaskList_DropReason_TextBox = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TaskTab_TaskList_DropReason_TextBox");
		InfoTab_StatusChangeNote_TextField = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "InfoTab_StatusChangeNote_TextField");
		SearchWorkOrder_WOStatus_DDValue = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SearchWorkOrder_WOStatus_DDValue");
		ANNLIST_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ANNLIST_Task_Details");
		First_PROFSVC_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "First_PROFSVC_Task_Details");
		Second_PROFSVC_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Second_PROFSVC_Task_Details");
		Third_PROFSVC_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Third_PROFSVC_Task_Details");
		First_PROFSVC_BillingCode = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "First_PROFSVC_BillingCode");
		First_PROFSVC_QuanityValue = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "First_PROFSVC_QuanityValue");
		Add_BillCode_Data = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Add_BillCode_Data");		
		EPSF_CollectionNotesSource_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_CollectionNotesSource_DD");
		Testing_Task_DD_Default_Value_Match = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Testing_Task_DD_Defalut_Value");		
		CertDecider_DDValue = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertDecider_DD");*/
	}	
	
	
	@Test( priority = 0, description="To Close Draft Status monitor Plan for specific client=36440")
	public void DB_ProcedureLaunch_ToClose_DraftMonitorPlan() throws Throwable {
		try{
			fnsApps_Report_Logs("################################## [BS - 01]  Create Active Monitor Plan");
			fnUpdateDB_By_Executing_Procedure_ToCloseMonitorPlan(Facility_ID);
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
	}
	
	
	


@Test( priority = 1, dependsOnMethods={"DB_ProcedureLaunch_ToClose_DraftMonitorPlan"})
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
		try{
			if (!IsBrowserPresentAlready) {		
				IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
				fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
			}
			fnsApps_Report_Logs("=========================================================================================================================================");
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
}	
	
	
	
	
	
	@Test(priority = 2, description="  [SearchWorkOrder_for_Facility_36440_in_DraftStatus_IfAnyThen_DropIt]",  dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"})
	public void SearchWorkOrder_for_Facility_36440_InDraftStatus_IfAnyThen_DropIt() throws Throwable{	
		try{
			Facility_ID = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Facility_LookUp").trim();
			Testing_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Testing_Task_Details");
			TaskTab_TaskList_DropReason_TextBox = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "TaskTab_TaskList_DropReason_TextBox");
			InfoTab_StatusChangeNote_TextField = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "InfoTab_StatusChangeNote_TextField");
			SearchWorkOrder_WOStatus_DDValue = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "SearchWorkOrder_WOStatus_DDValue");
			ANNLIST_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "ANNLIST_Task_Details");
			First_PROFSVC_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "First_PROFSVC_Task_Details");
			Second_PROFSVC_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Second_PROFSVC_Task_Details");
			Third_PROFSVC_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Third_PROFSVC_Task_Details");
			First_PROFSVC_BillingCode = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "First_PROFSVC_BillingCode");
			First_PROFSVC_QuanityValue = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "First_PROFSVC_QuanityValue");
			Add_BillCode_Data = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Add_BillCode_Data");		
			EPSF_CollectionNotesSource_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "EPSF_CollectionNotesSource_DD");
			Testing_Task_DD_Default_Value_Match = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Testing_Task_DD_Defalut_Value");		
			CertDecider_DDValue = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "CertDecider_DD");
			TC_Step=1;
			fnsApps_Report_Logs("################### Test Case ::::::1 SearchWorkOrder_for_Facility_36440_InDraftStatus_IfAnyThen_DropIt ");
			
			
			
			String SearchWorkOrder_WOStatus_DDValue_Select_Xpath = "//li[contains(text(), '"+SearchWorkOrder_WOStatus_DDValue+"')]";
		
			iteration=1;
			
			for(int start=0; start<iteration; start++){
				
				fnsSearchWorkOrder_Ajax_Link_Click();
				fnsLoading_Progressingwait(8);
				fnsGet_Element_Enabled("SearchWorkOrder_CorpFacility_TextField");
				fnsWait_and_Type("SearchWorkOrder_CorpFacility_TextField", Facility_ID);
				
				WithOut_OR_fnGet_Element_Enabled(SearchWorkOrder_WOStatus_DDValue_Select_Xpath);
				WithOut_OR_fnClick(SearchWorkOrder_WOStatus_DDValue_Select_Xpath);
				
				fnsGet_Element_Enabled("SearchWorkOrder_Search_Bttn");
				fnsWait_and_Click("SearchWorkOrder_Search_Bttn");
				
				fnsLoading_Progressingwait(10);
				fnsGet_Element_Enabled("SearchWorkOrder_SearchResult_TableHeader");
				iteration=fnsRetrun_TotalRowCount_OfTable("SearchWorkOrder_SearchResult_TableHeader");
				
				String WorkOrderIDText=fnsGet_Field_TEXT("SearchWorkOrder_SearchResult_Table_FirstCell").trim();
				
				if(!(WorkOrderIDText.contains("W"))){
					fnsApps_Report_Logs("PASSED == No WorkOrders are found in DRAFT Status on searchWOScreen");
					break;
				}
				
				fnsTable_ClickOn_LINK_ByMatchingText(WorkOrderIDText);
				fnsLoading_Progressingwait(3);
				fnsGet_Element_Enabled("Footer");
				fnsLoading_Progressingwait(2);
				
				//Clicking on WO Task Tab
				for(int clicktry=0; clicktry<25; clicktry++){
					fnsGet_Element_Enabled("ViewWorkOrder_Tasks_Tab");
					fnsWait_and_Click("ViewWorkOrder_Tasks_Tab");
					fnsLoading_Progressingwait(2);
					
					if( (driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_TaskList_MainTableHeader"))).size()>0) || (driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_ApplyTaskTemplate_Bttn"))).size()>0) ){
						break;
					}
					if(clicktry==24){
						isTestCasePass = false;
						fnsTake_Screen_Shot("TabOpenFail");
						throw new Exception("FAILED == <Tasks> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
					}
				}
				
			
				
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_ApplyTaskTemplate_Bttn"))).size()>0){
					fnsApps_Report_Logs("PASSED == There are not task to drop into the TASK TAB.");
				}else{
					fnsGet_Element_Enabled("ViewWorkOrder_TasksTab_TaskList_MainTableHeader");
					WOTaskCount=fnsRetrun_TotalRowCount_OfTable("ViewWorkOrder_TasksTab_TaskList_MainTableHeader");
					
					
					
					for(int i=1; i<=WOTaskCount; i++){
						fnsLoading_Progressingwait(2);	
						String RowTextXpath=OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_TaskList_MainTableCell")+"/tr["+i+"]";
						
						Delete_Bttn_Xpath = "//a[@id='mainForm:tabView:dataTable:"+(i-1)+":deleteTaskBtn']";
								
						if(driver.findElements(By.xpath(RowTextXpath)).size()>0){
							String RowText=WithOut_OR_fnsGet_Field_TEXT(RowTextXpath).trim();						
							if((RowText.contains("ANNLIST")) && !(RowText.contains("DROPPED")) && !(RowText.contains("VOID"))){
						
								WithOut_OR_fnGet_Element_Enabled(Delete_Bttn_Xpath);
								WithOut_OR_fnClick(Delete_Bttn_Xpath);
								Thread.sleep(2000);
								
								
								//New Code added 30.3.2016
								for(int Visiblity=0; Visiblity<=10; Visiblity++){
									if(driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn"))).isDisplayed()){
										fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
										Thread.sleep(1500);
										fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
										Thread.sleep(1500);
										fnsLoading_Progressingwait(6);
										break;
									}else if (driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox"))).isDisplayed()){
										break;
									}else{
										Thread.sleep(1000);
									}
								}
								
								
								
								
								/*
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
								fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
								TestSuiteBase_Aspirago.fnsLoading_Progressingwait(4);		
								*/
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
								fnsWait_and_Clear("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
								fnsWait_and_Type("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox", TaskTab_TaskList_DropReason_TextBox);
								
								
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
								fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
								fnsLoading_Progressingwait(4);
							}else if( !(RowText.contains("DROPPED")) && !(RowText.contains("VOID")) ){
								Thread.sleep(1000);
								try{
									assert (driver.findElements(By.xpath(Delete_Bttn_Xpath)).size()>0):"FAILED == Unable to drop task as DELETE button is not coming in Task No <"+i+">, please refer screenshot >>DropTaskFail"+fnsScreenShot_Date_format();
								}catch(Throwable t){
									fnsTake_Screen_Shot("DropTaskFail");
									fnsApps_Report_Logs(t.getMessage());
									throw new Exception (t.getMessage());
								}
													
								WithOut_OR_fnGet_Element_Enabled(Delete_Bttn_Xpath);
								WithOut_OR_fnClick(Delete_Bttn_Xpath);
								Thread.sleep(2000);
								
								//New Code added 30.3.2016
								for(int Visiblity=0; Visiblity<=10; Visiblity++){
									if(driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn"))).isDisplayed()){
										fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
										fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
										fnsLoading_Progressingwait(4);
										break;
									}else if (driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox"))).isDisplayed()){
										break;
									}else{
										Thread.sleep(1000);
									}
								}
												
								
								
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
								fnsWait_and_Clear("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
								fnsWait_and_Type("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox", TaskTab_TaskList_DropReason_TextBox);
								
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
								fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
								fnsLoading_Progressingwait(4);
								
							}
							for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2); wait++){
								if(driver.findElements(By.xpath(Delete_Bttn_Xpath)).size()>0){
									Thread.sleep(2000);
								}else {
									break;
								}
								if(wait==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)){
									isTestCasePass = false;
									fnsTake_Screen_Shot("Loading_not_disappear");
									throw new Exception("FAILED == Loading screen is not getting disappeared, Please refer screen shot >> Loading_not_disappear"+fnsScreenShot_Date_format());
								}
							}
							
						
						}else{
							break;
						}
						
					}
				}
				
				
					
			
				//Clicking info TAB
				for(int clicktry=0; clicktry<26; clicktry++){
						fnsGet_Element_Enabled("ViewWorkOrder_Info_Tab");
						//Thread.sleep(5000);
						
						fnsLoading_Progressingwait(2);
						fnsWait_and_Click("ViewWorkOrder_Info_Tab");
						fnsLoading_Progressingwait(2);
						
						if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("EditWorkOrder_InfoTab_WOStatus_DDClick"))).size()>0){
							break;
						}
						if(clicktry==25){
							isTestCasePass = false;
							fnsTake_Screen_Shot("TabOpenFail");
							throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
						}
					}
				
				
				
				fnsDD_value_Select_By_MatchingText("EditWorkOrder_InfoTab_WOStatus_DDClick", "EditWorkOrder_InfoTab_WOStatus_DDValue", "li", "DROP");
				
				fnsGet_Element_Enabled("EditWorkOrder_InfoTab_StatusChangeNote_TextField");
				fnsWait_and_Type("EditWorkOrder_InfoTab_StatusChangeNote_TextField", InfoTab_StatusChangeNote_TextField);
				

				//Added on8.8.2016
				fnsDD_value_Select_By_MatchingText("EditWorkOrder_InfoTab_CertDecider_DDClick", "EditWorkOrder_InfoTab_CertDecider_DDValue", "li", CertDecider_DDValue);
				
				
				fnsGet_Element_Enabled("EditWorkOrder_InfoTab_StandardVersion_Bttn");
				fnsWait_and_Click("EditWorkOrder_InfoTab_StandardVersion_Bttn");
				Thread.sleep(2000);
								
				fnsGet_Element_Enabled("EditWorkOrder_InfoTab_StandardVersion_ValueSelect");
				fnsWait_and_Click("EditWorkOrder_InfoTab_StandardVersion_ValueSelect");
				
				fnsGet_Element_Enabled("ViewWorkOrder_Task_Save_Bttn");
				fnsWait_and_Click("ViewWorkOrder_Task_Save_Bttn");
				fnsLoading_Progressingwait(2);
				fnsText_Fetch_and_Assert("Success_Message_Div", "success");	
				
		}	
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(t.getMessage());
	}
}	
	
	

	
	
	@Test(dependsOnMethods={"SearchWorkOrder_for_Facility_36440_InDraftStatus_IfAnyThen_DropIt"}, priority = 3, description="  [Create Monitor Plan -- Verify data updated successfully and New Monitor Plan Created.]")
	public void CreateMonitorPlan_Verify_NewMonitorPlanCreated_and_inDraftStatus() throws Throwable{
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			fnsApps_Report_Logs("################### Test Case ::::::2 CreateMonitorPlan_Verify_NewMonitorPlanCreated_and_inDraftStatus ");
		
		
			//##################################### TC01
			//Facility_ID = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Facility_LookUp").trim();
			fnsLoading_Progressingwait(1);
			fnsCreateAnnualMonitorPlan_Ajax_Link_Click();
			fnsLoading_Progressingwait(3);
			
			//Click and select value from lookup
			fnsGet_Element_Enabled("CreateAnnualMonitorPlan_NewMonitorPlan_Facility_LookUp_Bttn");
			fnsWait_and_Click("CreateAnnualMonitorPlan_NewMonitorPlan_Facility_LookUp_Bttn");
			fnsLoading_Progressingwait(3);
			fnsGet_Element_Enabled("CreateAnnualMonitorPlan_NewMonitorPlan_Facility_LookUp_TextField");
			fnsWait_and_Type("CreateAnnualMonitorPlan_NewMonitorPlan_Facility_LookUp_TextField", Facility_ID);
			fnsLookup_RadioBttn_Select();
			
			
			
			//Wait till RevenueProgram_DD enabled and select value from DD
			while(true){
				int start=0;
				fnsLoading_Progressingwait(2);
				WebElement FAcilityField = fnsGet_OR_MtrPlan_ObjectX("CreateAnnualMonitorPlan_NewMonitorPlan_Facility_TEXTField");
				FacilityFieldText=FAcilityField.getAttribute("value").trim();
			
				if( (FacilityFieldText.length()>3) ){
					fnsDD_value_Select_By_MatchingText("CreateAnnualMonitorPlan_NewMonitorPlan_RevenueProgram_DDClick", "CreateAnnualMonitorPlan_NewMonitorPlan_RevenueProgram_DDValue", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "RevenueProgram_DD"));
					break;}
				else{
					Thread.sleep(100);}
				if(start>10){
					break;}
				start++;
			}
			
			
					
			fnsWait_and_Click("CreateAnnualMonitorPlan_Create_Bttn");
			fnsLoading_Progressingwait(5);
			
			
			//##################################### TC02
			fnsGet_Element_Enabled("Footer");
			fnsGet_Element_Enabled("Success_Message_Div");
			fnsText_Fetch_and_Assert("Success_Message_Div", "success");
			
			//Verify MonitorId created and in Dreaft Status and Stored in a String
			Monitor_ID=fnsGet_Field_TEXT("EditAnnualMonitorPlan_MonitorPlanId_Xpath").trim();
			try{
				assert !(Monitor_ID.equalsIgnoreCase("")):"FAILED == Monitor ID is not Created, plz see screenshot [" +Monitor_ID + fnsScreenShot_Date_format() + "]";
				fnsApps_Report_Logs("PASSED == Successfully Created Monitor ID and it is("+Monitor_ID+").");
			}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot(Monitor_ID);
				fnsApps_Report_Logs("FAILED == Monitor ID is not Created, plz see screenshot [" +Monitor_ID + fnsScreenShot_Date_format() + "], Getting Exception >> "+t.getMessage());
				throw new Exception("FAILED == Monitor ID is not Created, plz see screenshot [" +Monitor_ID + fnsScreenShot_Date_format() + "], Getting Exception >> "+t.getMessage());}
			
			fnsText_Fetch_and_Assert("EditAnnualMonitorPlan_Status_Xpath", "draft");	
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"CreateMonitorPlan_Verify_NewMonitorPlanCreated_and_inDraftStatus"}, priority = 4, description="  [Edit Monitor Plan >> Verify TaskTab Data are Updated Sccessfully.]")
	public void EditMonitorPlan_Verify_TasksTab_DataUpdatedSccessfully() throws Throwable{
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			fnsApps_Report_Logs("################### Test Case ::::::3 EditMonitorPlan_Verify_TasksTab_DataUpdatedSccessfully ");
			
			fnsGet_Element_Enabled("EditAnnualMonitorPlan_Tasks_TAB");
			
			//Clicking Tasks TAB
			for(int clicktry=0; clicktry<6; clicktry++){
					fnsWait_and_Click("EditAnnualMonitorPlan_Tasks_TAB");
					fnsLoading_Progressingwait(3);
											
					if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("TasksTab_ApplyTaskTemplate_Bttn"))).size()>0){
						break;
					}
					if(clicktry==5){
						isTestCasePass = false;
						fnsTake_Screen_Shot("TabOpenFail");
						throw new Exception("FAILED == <Tasks> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
					}
			}
			
			fnsGet_Element_Enabled("Footer");
			fnsGet_Element_Enabled("TasksTab_ApplyTaskTemplate_Bttn");
			fnsWait_and_Click("TasksTab_ApplyTaskTemplate_Bttn");
			fnsLoading_Progressingwait(2);
			
			
			TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TasksTab_ApplyTaskTemplate_Popup_Title"), "Select Tasks", "'Select Tasks' Popup is not getting opened");
			
			//Clicking PROFSVE CheckBox from SelectTask PopUp
			int CheckBoxInRowNo=fnsReturn_RowNumber_By_TableMatching_TEXT("TasksTab_ApplyTaskTemplate_SelectTasks_TableHeader_Data", "PROFSVC");
			String CheckBoxXpath=OR_MtrPlan.getProperty("TasksTab_ApplyTaskTemplate_SelectTasks_PROFSVC_CheckBox")+(CheckBoxInRowNo-1)+":mptasktmpl']/div[2]";
			WithOut_OR_fnClick(CheckBoxXpath);
					
			String QuantityXpath=OR_MtrPlan.getProperty("TasksTab_ApplyTaskTemplate_SelectTasks_PROFSVC_QuantityField")+(CheckBoxInRowNo-1)+":mptoccurences']";
			WithOut_OR_fnClear(QuantityXpath);
			WithOut_OR_fnType(QuantityXpath, "3");
			
			fnsWait_and_Click("TasksTab_ApplyTaskTemplate_SelectTasks_Continue_Bttn");
			fnsLoading_Progressingwait(2);
			
			//Verify Records are updated into Task list table
			fnsGet_Element_Enabled("TasksTab_TaskList_TableHeader_Xpath");
			Integer TaskListTableRowCount = fnsRetrun_TotalRowCount_OfTable("TasksTab_TaskList_TableHeader_Xpath");
			try{
				assert !(TaskListTableRowCount.equals(0)):"FAILED == Tasks are not getting added into TaskList Table, plz see screenshot [TaskListTableUpdateFail" + fnsScreenShot_Date_format() + "]";
			}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("TaskListTableUpdateFail");
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());}
			
			//Edit Annual Listing Task and add data into it.
			fnc_TaskTAB_EditTask_and_Enter_Data_in_StartYear_Frenquency_EndYear("TasksTab_TaskList_ANNLIST_Edit_Bttn", "Edit Monitor Plan Task -- Annual Listing", (ANNLIST_Task_Details.split(",")[0]), (ANNLIST_Task_Details.split(",")[1]), (ANNLIST_Task_Details.split(",")[2]));
			
			fnVerify_BillingCode_Updated_through_AddSingle_Bttn
			 (0, (ANNLIST_Task_Details.split(",")[3]), 
			 (ANNLIST_Task_Details.split(",")[4]), 
			 (ANNLIST_Task_Details.split(",")[5]),
			 (ANNLIST_Task_Details.split(",")[6]),
			 (ANNLIST_Task_Details.split(",")[7]));
			
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsLoading_Progressingwait(3);
			Thread.sleep(1000);
			fnsLoading_Progressingwait(1);
			
			
			
			//Edit First PROFSVC Task and add data into it.
			fnc_TaskTAB_EditTask_and_Enter_Data_in_StartYear_Frenquency_EndYear("TasksTab_TaskList_First_PROFSVC_Edit_Bttn", "Edit Monitor Plan Task -- First PROFSVC", (First_PROFSVC_Task_Details.split(",")[0]), (First_PROFSVC_Task_Details.split(",")[1]), (First_PROFSVC_Task_Details.split(",")[2]));
			
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsLoading_Progressingwait(3);
			Thread.sleep(1000);
			fnsLoading_Progressingwait(1);
			
			//Edit Second PROFSVC Task and add data into it.
			fnc_TaskTAB_EditTask_and_Enter_Data_in_StartYear_Frenquency_EndYear("TasksTab_TaskList_Second_PROFSVC_Edit_Bttn", "Edit Monitor Plan Task -- Second PROFSVC", (Second_PROFSVC_Task_Details.split(",")[0]), (Second_PROFSVC_Task_Details.split(",")[1]), (Second_PROFSVC_Task_Details.split(",")[2]));
					
			fnVerify_BillingCode_Updated_through_AddSingle_Bttn
			(0, (Second_PROFSVC_Task_Details.split(",")[3]), 
			(Second_PROFSVC_Task_Details.split(",")[4]), 
			(Second_PROFSVC_Task_Details.split(",")[5]),
			(Second_PROFSVC_Task_Details.split(",")[6]),
			(Second_PROFSVC_Task_Details.split(",")[7]));
			
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsLoading_Progressingwait(3);
			Thread.sleep(1000);
			fnsLoading_Progressingwait(1);
			
			//Edit Third PROFSVC Task and add data into it.
			fnc_TaskTAB_EditTask_and_Enter_Data_in_StartYear_Frenquency_EndYear("TasksTab_TaskList_Third_PROFSVC_Edit_Bttn", "Edit Monitor Plan Task -- Third PROFSVC", (Third_PROFSVC_Task_Details.split(",")[0]), (Third_PROFSVC_Task_Details.split(",")[1]), (Third_PROFSVC_Task_Details.split(",")[2]));
			
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsLoading_Progressingwait(3);
			Thread.sleep(1000);
			fnsLoading_Progressingwait(1);
			
			
			//Adding 6 billing codes into First PROFSVC Task
			fnsGet_Element_Enabled("TasksTab_TaskList_First_PROFSVC_Edit_Bttn");
			fnsWait_and_Click("TasksTab_TaskList_First_PROFSVC_Edit_Bttn");
			
			TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TasksTab_EditMonitorPlanTask_Popup_Tilte"), "Edit Monitor Plan Task", "'Edit Monitor Plan Task -- First PROFSVC' Popup is not getting opened");
			
			for(int i=0; i<6; i++){
				String BillingCode=(First_PROFSVC_BillingCode.split(",")[i]);
				String QuanityValue=(First_PROFSVC_QuanityValue.split(",")[i]);
				String DiscountValue=(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "First_PROFSVC_DiscountValue").split(",")[i]);
				String DiscountTypeDDValue=(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "First_PROFSVC_DiscountTypeDDValue").split(",")[i]);
				String DiscountExpirationYearDDValue=(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "First_PROFSVC_DiscountExpirationYearDDValue").split(",")[i]);
				int BillingCodeXpathValue=i;
				
				fnVerify_BillingCode_Updated_through_AddSingle_Bttn(BillingCodeXpathValue, BillingCode, QuanityValue, DiscountValue, DiscountTypeDDValue, DiscountExpirationYearDDValue);
			}
			
	
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsLoading_Progressingwait(3);
			Thread.sleep(1000);
			fnsLoading_Progressingwait(1);
			
			
			
			AnnList_BillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(1, (ANNLIST_Task_Details.split(",")[3]) );	
			
			ProSrvc_FirstBillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(2, First_PROFSVC_BillingCode.split(",")[0]);
			ProSrvc_SecondBillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(2, First_PROFSVC_BillingCode.split(",")[1]);
			ProSrvc_ThirdBillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(2, First_PROFSVC_BillingCode.split(",")[2]);
			ProSrvc_FourthBillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(2, First_PROFSVC_BillingCode.split(",")[3]);
			ProSrvc_FifthBillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(2, First_PROFSVC_BillingCode.split(",")[4]);
			ProSrvc_SixthBillCode_PriceValue=fnVerify_Fetch_PriceValues_of_BillingCode(2, First_PROFSVC_BillingCode.split(",")[5]);
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}	
}
	
	


	@Test(dependsOnMethods={"EditMonitorPlan_Verify_TasksTab_DataUpdatedSccessfully"}, priority = 5, description="  [Edit Monitor Plan >> Verify Testing Tasks Tab Data are Updated Sccessfully.]")
	public void EditMonitorPlan_Verify_TestingTasksTab_and_EPSF_DataUpdatedSccessfully() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::4 EditMonitorPlan_Verify_TestingTasksTab_and_EPSF_DataUpdatedSccessfully ");

		try{
			//Testing TAB --- Add and Edit Task and verify dd default values and a new bill code.
			fnc_TestingTAB_AddEditTask_Verify_DDDefaultValue_and_Bill_Code();
			
		
			
			//EPSF data enter and save success
			fnsGet_Element_Enabled("EPSFDetails_Bttn");
			fnsWait_and_Click("EPSFDetails_Bttn");
			fnsLoading_Progressingwait(5);	
			
			//Added on 1.5.2017 as new mandatory look up 'monitor Code' is coming		
			fnsEPSF_LookUp_Bttn_Click("Monitor Code");
			fnsGet_Element_Enabled("EPSFFields_MonitorCode_LK_Value");
			fnsWait_and_Type("EPSFFields_MonitorCode_LK_Value", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_MonitorCode_LK"));
			try{
				fnsLookup_RadioBttn_Select();
			}catch(Throwable t){
				throw new Exception("FAILED == 'Monitor Code' lookUp radio button select failed, getting exception >> "+Throwables.getStackTraceAsString(t));
			}
			
			fnsLoading_Progressingwait(5);
			
						
					
			/*fnsGet_Element_Enabled("EPSFFields_PrimaryDCCNumber_LK_Bttn");
			fnsWait_and_Click("EPSFFields_PrimaryDCCNumber_LK_Bttn");
			fnsLoading_Progressingwait(3);*/
			
			
			
			fnsEPSF_LookUp_Bttn_Click("Parent DCC Number");
			fnsGet_Element_Enabled("EPSFFields_PrimaryDCCNumber_LK_Value");
			fnsWait_and_Type("EPSFFields_PrimaryDCCNumber_LK_Value", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_PrimaryDCCNumber_LK"));
			try{
				fnsLookup_RadioBttn_Select();
			}catch(Throwable t){
				throw new Exception("FAILED == 'ParentDCCNumber' lookUp radio button select failed, getting exception >> "+Throwables.getStackTraceAsString(t));
			}
			
			fnsLoading_Progressingwait(2);
			Thread.sleep(10000);
			
			
			/*fnsGet_Element_Enabled("EPSFFields_SampleCode_LK_Bttn");
			fnsWait_and_Click("EPSFFields_SampleCode_LK_Bttn");
			fnsLoading_Progressingwait(3);*/
			
			
			fnsEPSF_LookUp_Bttn_Click("Sample Code");
			fnsGet_Element_Enabled("EPSFFields_SampleCode_LK_Value");
			fnsWait_and_Type("EPSFFields_SampleCode_LK_Value", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_SampleCode_LK"));
			
			try{
				fnsLookup_RadioBttn_Select();
			}catch(Throwable t){
				throw new Exception("FAILED == 'SampleCode' lookUp radio button select failed, getting exception >> "+Throwables.getStackTraceAsString(t));
			}
			
			
			Thread.sleep(3000);
			
			//Commented on 3.11.2016 as 36440 replace with C0176083
			/*fnsDD_value_Select_By_MatchingText("EPSFFields_CollectionNotesSource_DDClick", "EPSFFields_CollectionNotesSource_DDValue", "li",  (EPSF_CollectionNotesSource_DD.split(",")[0]));
			
			fnsGet_Element_Enabled("EPSFFields_ProdTrack_LK_Bttn");
			fnsWait_and_Click("EPSFFields_ProdTrack_LK_Bttn");
			TestSuiteBase_Aspirago.fnsLoading_Progressingwait(3);	
			fnsGet_Element_Enabled("lookup_select_radio_bttn");
			fnsWait_and_Click("lookup_select_radio_bttn");*/
			
			fnsLoading_Progressingwait(2);
			fnsDD_value_Select_By_MatchingText("EPSFFields_CollectionNotesSource_DDClick", "EPSFFields_CollectionNotesSource_DDValue", "li", (EPSF_CollectionNotesSource_DD.split(",")[1]));
			
			fnsGet_Element_Enabled("EPSFFields_PerformanceCollectionNotes_LK_Bttn");
			fnsWait_and_Click("EPSFFields_PerformanceCollectionNotes_LK_Bttn");
			fnsLoading_Progressingwait(3);
			fnsGet_Element_Enabled("EPSFFields_PerformanceCollectionNotes_LK_Value");
			fnsWait_and_Type("EPSFFields_PerformanceCollectionNotes_LK_Value", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_PerformanceCollectionNotes_LK"));
			try{
				fnsLookup_RadioBttn_Select();
			}catch(Throwable t){
				throw new Exception("FAILED == 'PerformanceCollectionNotes' lookUp radio button select failed, getting exception >> "+Throwables.getStackTraceAsString(t));
			}
			
			fnsLoading_Progressingwait(2);
			fnsDD_value_Select_By_MatchingText("EPSFFields_CollectionNotesSource_DDClick", "EPSFFields_CollectionNotesSource_DDValue", "li", (EPSF_CollectionNotesSource_DD.split(",")[2]));
			fnsGet_Element_Enabled("EPSFFields_FreeText_TextBox");
			fnsWait_and_Type("EPSFFields_FreeText_TextBox", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_FreeText_TextBox"));
			
		
			fnsDD_value_Select_By_MatchingText("EPSFFields_CollectionNotesSource_DDClick", "EPSFFields_CollectionNotesSource_DDValue", "li",(EPSF_CollectionNotesSource_DD.split(",")[3]));
			fnsGet_Element_Enabled("EPSFFields_SampleCode_TextBox");
			fnsWait_and_Type("EPSFFields_SampleCode_TextBox", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EPSF_SampleCode_TextBox"));
			
			fnsGet_Element_Enabled("EPSFDetails_Save_Bttn");
			fnsWait_and_Click("EPSFDetails_Save_Bttn");
			fnsLoading_Progressingwait(3);
			fnsText_Fetch_and_Assert("EPSF_Success_Message_Div", "success");
			
			fnsGet_Element_Enabled("EPSFFields_Close_Bttn");
			fnsWait_and_Click("EPSFFields_Close_Bttn");
			Thread.sleep(3000);
			
			fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsWait_and_Click("TestingTaskTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
			fnsLoading_Progressingwait(3);
			Thread.sleep(1000);
			fnsLoading_Progressingwait(1);
		}catch(Throwable t){
			throw new Exception (Throwables.getStackTraceAsString(t));
			
		}
}
	

	

	@Test(dependsOnMethods={"EditMonitorPlan_Verify_TestingTasksTab_and_EPSF_DataUpdatedSccessfully"}, priority = 6, description="  [Edit Monitor Plan >> Verify Info Tab Data are Updated Sccessfully.]")
	public void EditMonitorPlan_Verify_InfoTab_DataUpdatedSccessfully() throws Throwable{
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			fnsApps_Report_Logs("################### Test Case ::::::5 EditMonitorPlan_Verify_InfoTab_DataUpdatedSccessfully ");
	
			
			fnsGet_Element_Enabled("EditAnnualMonitorPlan_Info_TAB");
						
			//Clicking info TAB
			for(int clicktry=0; clicktry<6; clicktry++){
					fnsWait_and_Click("EditAnnualMonitorPlan_Info_TAB");
					fnsLoading_Progressingwait(2);
							
					if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("InfoTab_Status_DDClick"))).size()>0){
						break;
					}
					if(clicktry==5){
						isTestCasePass = false;
						fnsTake_Screen_Shot("TabOpenFail");
						throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
					}
			}
					
			fnsGet_Element_Enabled("Footer");
			
			fnsGet_Element_Enabled("InfoTab_Status_DDClick");
			fnsDD_value_Select_By_MatchingText("InfoTab_Status_DDClick", "InfoTab_Status_DDValue", "li", "Active");
			
			fnsWait_and_Click("InfoTab_Save_Bttn");
			fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("Footer");
			fnsGet_Element_Enabled("Success_Message_Div");
			fnsText_Fetch_and_Assert("Success_Message_Div", "success");
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
}
	

	
	
	
	@Test(dependsOnMethods={"EditMonitorPlan_Verify_InfoTab_DataUpdatedSccessfully"}, priority = 7, description="  [Edit Monitor Plan >> Verify Snapshot Tab Data are Updated Sccessfully.]")
	public void EditMonitorPlan_Verify_SnapshotTab_DataUpdatedSccessfully() throws Throwable{
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			fnsApps_Report_Logs("################### Test Case ::::::6 EditMonitorPlan_Verify_SnapshotTab_DataUpdatedSccessfully ");
			
			fnsGet_Element_Enabled("EditAnnualMonitorPlan_Snapshot_TAB");
			
			//Clicking Testing Tasks TAB
			for(int clicktry=0; clicktry<6; clicktry++){
					fnsWait_and_Click("EditAnnualMonitorPlan_Snapshot_TAB");
					fnsLoading_Progressingwait(2);
											
					if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("SnapshotTab_TaskSummary_ChooseYear_DDClick"))).size()>0){
						break;
					}
					if(clicktry==5){
						isTestCasePass = false;
						fnsTake_Screen_Shot("TabOpenFail");
						throw new Exception("FAILED == <Snapshot> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
					}
			}
							
			fnsGet_Element_Enabled("Footer");
			
			fnsDD_value_Select_By_MatchingText("SnapshotTab_TaskSummary_ChooseYear_DDClick", "SnapshotTab_TaskSummary_ChooseYear_DDValue", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TaskSummary_ChooseYear_DD"));
							
			fnsGet_Element_Enabled("SnapshotTab_TaskSummary_CreateWorkOrder_bttn");
			fnsWait_and_Click("SnapshotTab_TaskSummary_CreateWorkOrder_bttn");
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled("SnapshotTab_TaskSummary_CreateWorkOrder_ConfirmationBox_Yes_Bttn");
			fnsWait_and_Click("SnapshotTab_TaskSummary_CreateWorkOrder_ConfirmationBox_Yes_Bttn");
			fnsLoading_Progressingwait(5);
			
			fnsGet_Element_Enabled("Footer");
			Thread.sleep(3000);
			fnsLoading_Progressingwait(2);
			
			if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_MainErrorMsg"))).size()>0){
				fnsTake_Screen_Shot("Error_Coming");
				fnsApps_Report_Logs("FAILED == Create work order fail, please refer screen shot >> Error_Coming"+fnsScreenShot_Date_format());
				throw new Exception("FAILED == Create work order fail, please refer screen shot >> Error_Coming"+fnsScreenShot_Date_format());
			}
			
			WO_Drop_Bs_02_Flag = false;
			
			
			fnsGet_Element_Enabled("WorkOrder_IdXpath");
			WorkOrderId=(fnsGet_Field_TEXT("WorkOrder_IdXpath").split("\\-")[0]).trim();
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
}
	
	
	
	
	@Test(dependsOnMethods={"EditMonitorPlan_Verify_SnapshotTab_DataUpdatedSccessfully"}, priority = 8, description="  [Edit Monitor Plan >> CreateWorkOrder >> Verify Data saved in Monitor Plan are correctly displayed in WorkOrder TaskTab.]")
	public void EditMonitorPlan_Verify_MonitorPlanSavedData_CorrectlyDisplay_in_WorkOrderTaskTab() throws Throwable{
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			fnsApps_Report_Logs("################### Test Case ::::::7 EditMonitorPlan_Verify_MonitorPlanSavedData_CorrectlyDisplay_in_WorkOrderTaskTab ");
		
			
			//Clicking on WO Task Tab
			for(int clicktry=0; clicktry<26; clicktry++){
				fnsWait_and_Click("ViewWorkOrder_Tasks_Tab");
				fnsLoading_Progressingwait(2);
				
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_TaskList_MainTableHeader"))).size()>0){
					break;
				}
				if(clicktry==25){
					isTestCasePass = false;
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == <Tasks> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
				}
			}
					
			
			//Verify Tasks are updated into WO Task list table
			fnsGet_Element_Enabled("ViewWorkOrder_TasksTab_TaskList_MainTableHeader");
			Integer WOTaskListTableRowCount=fnsRetrun_TotalRowCount_OfTable("ViewWorkOrder_TasksTab_TaskList_MainTableHeader");
			try{
				assert (WOTaskListTableRowCount.equals(3)):"FAILED == ANNLIST/PROFSVC  Task is not displayed in WorkOrder, plz see screenshot [TaskNotDisplay" + fnsScreenShot_Date_format();
				fnsText_Fetch_and_Assert("ViewWorkOrder_TaskTab_TaskList_ANNLIST_ColumnXpath", "ANNLIST");
				fnsText_Fetch_and_Assert("ViewWorkOrder_TaskTab_TaskList_PROFSVC_ColumnXpath", "PROFSVC");
				fnsApps_Report_Logs("PASSED == ANNLIST and PROFSVC  both Tasks are displayed in WorkOrder.");
			}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("TaskNotDisplay");
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());}
					
			Integer loopCounter = 1;
			boolean ExpandAll_Success = false;
			
			while(true){
				fnsGet_Element_Enabled("ViewWorkOrder_ShowAll_Link");
				fnsWait_and_Click("ViewWorkOrder_ShowAll_Link");
							
				try{
					for(int l=1; l<=60; l++){
						if(driver.findElements(By.xpath("//table[@id='mainForm:tabView:dataTable:0:basicPanel']")).size()>0){
							ExpandAll_Success = true;
							break;
						}else{
							Thread.sleep(1000);
						}
					}
				}catch(Throwable t){
					//nothing to do
				}
				if(ExpandAll_Success){
					break;
				}
				
				if(loopCounter==5){
					fnsTake_Screen_Shot("Show_HideAll_Fail");
					fnsApps_Report_Logs("FAILED == after clicking on 'Show/HideAll', table is not getting expand ,please refer the screenshot >>  Show_HideAll_Fail"+fnsScreenShot_Date_format());
					throw new Exception ("FAILED == after clicking on 'Show/HideAll', table is not getting expand ,please refer the screenshot >>  Show_HideAll_Fail"+fnsScreenShot_Date_format());
				}
				loopCounter++;
			}
			
			
			
			
			for(int i=0; i<2; i++){
				String TaskDetailsXpath=".//*[@id='mainForm:tabView:dataTable:"+i+":basicPanel']/tbody/tr/td";
				String TaskDetailsText=WithOut_OR_fnsGet_Field_TEXT(TaskDetailsXpath).toLowerCase().trim();
				
				if(TaskDetailsText.contains("annlist")){
					ANNLIST_TaskTableNo=i;}
				if(TaskDetailsText.contains("profsvc")){
					PROFSVC_TaskTableNo=i;}
			}
			
			
			
			WithOut_OR_fnVerify_TaskList_TableData(ANNLIST_TaskTableNo, (ANNLIST_Task_Details.split(",")[3]), (ANNLIST_Task_Details.split(",")[4]), AnnList_BillCode_PriceValue);
			
			WithOut_OR_fnVerify_TaskList_TableData(PROFSVC_TaskTableNo, (First_PROFSVC_BillingCode.split(",")[0]), (First_PROFSVC_QuanityValue.split(",")[0]), ProSrvc_FirstBillCode_PriceValue);
			WithOut_OR_fnVerify_TaskList_TableData(PROFSVC_TaskTableNo, (First_PROFSVC_BillingCode.split(",")[1]), (First_PROFSVC_QuanityValue.split(",")[1]), ProSrvc_SecondBillCode_PriceValue);
			WithOut_OR_fnVerify_TaskList_TableData(PROFSVC_TaskTableNo, (First_PROFSVC_BillingCode.split(",")[2]), (First_PROFSVC_QuanityValue.split(",")[2]), ProSrvc_ThirdBillCode_PriceValue);
			WithOut_OR_fnVerify_TaskList_TableData(PROFSVC_TaskTableNo, (First_PROFSVC_BillingCode.split(",")[3]), (First_PROFSVC_QuanityValue.split(",")[3]), ProSrvc_FourthBillCode_PriceValue);
			WithOut_OR_fnVerify_TaskList_TableData(PROFSVC_TaskTableNo, (First_PROFSVC_BillingCode.split(",")[4]), (First_PROFSVC_QuanityValue.split(",")[4]), ProSrvc_FifthBillCode_PriceValue);
			WithOut_OR_fnVerify_TaskList_TableData(PROFSVC_TaskTableNo, (First_PROFSVC_BillingCode.split(",")[5]), (First_PROFSVC_QuanityValue.split(",")[5]), ProSrvc_SixthBillCode_PriceValue);
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
}
	
	
	
	
	@Test(dependsOnMethods={"EditMonitorPlan_Verify_MonitorPlanSavedData_CorrectlyDisplay_in_WorkOrderTaskTab"}, priority = 9, description="  [Edit Monitor Plan >> CreateWorkOrder >> Drop Work Order.]")
	public void EditMonitorPlan_Verify_WorkOrder_Drop_Successfully() throws Throwable{
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			fnsApps_Report_Logs("################### Test Case ::::::8 EditMonitorPlan_Verify_WorkOrder_Drop_Successfully ");
			
			
			Integer loopCounter = 1;
			boolean ExpandAll_Success = false;
			
			while(true){
				fnsGet_Element_Enabled("ViewWorkOrder_ShowAll_Link");
				fnsWait_and_Click("ViewWorkOrder_ShowAll_Link");
							
				try{
					for(int l=1; l<=10; l++){
						if(driver.findElements(By.xpath("//table[@id='mainForm:tabView:dataTable:0:basicPanel']")).size()==0){
							ExpandAll_Success = true;
							break;
						}else{
							Thread.sleep(1000);
						}
					}
				}catch(Throwable t){
					//nothing to do
				}
				if(ExpandAll_Success){
					break;
				}
				
				if(loopCounter==12){
					fnsTake_Screen_Shot("Show_HideAll_Fail");
					fnsApps_Report_Logs("FAILED == after clicking on 'Show/HideAll', table is not getting collasped ,please refer the screenshot >>  Show_HideAll_Fail"+fnsScreenShot_Date_format());
					throw new Exception ("FAILED == after clicking on 'Show/HideAll', table is not getting collasped ,please refer the screenshot >>  Show_HideAll_Fail"+fnsScreenShot_Date_format());
				}
				loopCounter++;
			}
			
			
	
			fnsGet_Element_Enabled("ViewWorkOrder_TasksTab_TaskList_MainTableHeader");
			WOTaskCount=fnsRetrun_TotalRowCount_OfTable("ViewWorkOrder_TasksTab_TaskList_MainTableHeader");
			
			for(int i=1; i<=WOTaskCount; i++){
				fnsLoading_Progressingwait(2);	
				String RowTextXpath=OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_TaskList_MainTableCell")+"/tr["+i+"]";
				Delete_Bttn_Xpath = "//a[@id='mainForm:tabView:dataTable:"+(i-1)+":deleteTaskBtn']";
						
				if(driver.findElements(By.xpath(RowTextXpath)).size()>0){
					String RowText=WithOut_OR_fnsGet_Field_TEXT(RowTextXpath).trim();						
					if((RowText.contains("ANNLIST")) && !(RowText.contains("DROPPED")) && !(RowText.contains("VOID"))){
										
						WithOut_OR_fnGet_Element_Enabled(Delete_Bttn_Xpath);
						WithOut_OR_fnClick(Delete_Bttn_Xpath);
						Thread.sleep(2000);
					
						//New Code added 30.3.2016
						for(int Visiblity=0; Visiblity<=20; Visiblity++){
							if(driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn"))).isDisplayed()){
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
								fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
								fnsLoading_Progressingwait(4);
								break;
							}else if (driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox"))).isDisplayed()){
								break;
							}else{
								Thread.sleep(1000);
							}
						}
						
						/*fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
						fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
						TestSuiteBase_Aspirago.fnsLoading_Progressingwait(4);		*/
						
						fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
						fnsWait_and_Clear("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
						fnsWait_and_Type("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox", TaskTab_TaskList_DropReason_TextBox);
						
						
						fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
						fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
						fnsLoading_Progressingwait(4);
					}else if( !(RowText.contains("DROPPED")) && !(RowText.contains("VOID")) ){
						Thread.sleep(1000);
						try{
							assert (driver.findElements(By.xpath(Delete_Bttn_Xpath)).size()>0):"FAILED == Unable to drop the task as 'DELETE' button is not coming in Task No <"+i+">, please refer screenshot >>DropTaskFail"+fnsScreenShot_Date_format();
						}catch(Throwable t){
							fnsTake_Screen_Shot("DropTaskFail");
							fnsApps_Report_Logs(t.getMessage());
							throw new Exception (t.getMessage());
						}
					
						WithOut_OR_fnGet_Element_Enabled(Delete_Bttn_Xpath);
						WithOut_OR_fnClick(Delete_Bttn_Xpath);
						Thread.sleep(2000);
						
						//New Code added 30.3.2016
						for(int Visiblity=0; Visiblity<=10; Visiblity++){
							if(driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn"))).isDisplayed()){
								fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
								fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropConfirmation_YES_Bttn");
								fnsLoading_Progressingwait(4);
								break;
							}else if (driver.findElement(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox"))).isDisplayed()){
								break;
							}else{
								Thread.sleep(1000);
							}
						}
						
						
						fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
						fnsWait_and_Clear("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox");
						fnsWait_and_Type("ViewWorkOrder_TaskTab_TaskList_DropReason_TextBox", TaskTab_TaskList_DropReason_TextBox);
						
						fnsGet_Element_Enabled("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
						fnsWait_and_Click("ViewWorkOrder_TaskTab_TaskList_DropReason_YES_Bttn");
						fnsLoading_Progressingwait(4);
					}
					
					for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2); wait++){
						if(driver.findElements(By.xpath(Delete_Bttn_Xpath)).size()>0){
							Thread.sleep(2000);
						}else {
							break;
						}
						if(wait==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)){
							isTestCasePass = false;
							fnsTake_Screen_Shot("Loading_not_disappear");
							throw new Exception("FAILED == Loading screen is not getting disappeared, Please refer screen shot >> Loading_not_disappear"+fnsScreenShot_Date_format());
						}
					}
				}else{
					break;
				}
				
			}
			
			
			
			
			//Clicking info TAB
			for(int clicktry=0; clicktry<26; clicktry++){
				fnsGet_Element_Enabled("ViewWorkOrder_Info_Tab");
			//	Thread.sleep(5000);
				fnsLoading_Progressingwait(1);
				fnsWait_and_Click("ViewWorkOrder_Info_Tab");
				fnsLoading_Progressingwait(3);
				
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("EditWorkOrder_InfoTab_WOStatus_DDClick"))).size()>0){
					break;
				}
				if(clicktry==25){
					isTestCasePass = false;
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
				}
			}
			
			
			
			fnsDD_value_Select_By_MatchingText("EditWorkOrder_InfoTab_WOStatus_DDClick", "EditWorkOrder_InfoTab_WOStatus_DDValue", "li", "DROP");
			
			fnsGet_Element_Enabled("EditWorkOrder_InfoTab_StatusChangeNote_TextField");
			fnsWait_and_Type("EditWorkOrder_InfoTab_StatusChangeNote_TextField", InfoTab_StatusChangeNote_TextField);
			
			//Added on8.8.2016
			fnsDD_value_Select_By_MatchingText("EditWorkOrder_InfoTab_CertDecider_DDClick", "EditWorkOrder_InfoTab_CertDecider_DDValue", "li", CertDecider_DDValue);
					
			
			fnsGet_Element_Enabled("EditWorkOrder_InfoTab_StandardVersion_Bttn");
			fnsWait_and_Click("EditWorkOrder_InfoTab_StandardVersion_Bttn");
			Thread.sleep(2000);
					
			fnsGet_Element_Enabled("EditWorkOrder_InfoTab_StandardVersion_ValueSelect");
			fnsWait_and_Click("EditWorkOrder_InfoTab_StandardVersion_ValueSelect");
			
			fnsGet_Element_Enabled("ViewWorkOrder_Task_Save_Bttn");
			fnsWait_and_Click("ViewWorkOrder_Task_Save_Bttn");
			fnsLoading_Progressingwait(2);
			fnsText_Fetch_and_Assert("Success_Message_Div", "success");		
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
}
	

	
	
	
//########################################################################################################################################################################################
//############################################################   Class Function ##########################################################################################################

//Verify Drop down default value by DD label	
	public void fnc_Verify_DD_Defalut_Value_TestingTaskTab_By_Label(String DropDown_Xpath, String Matching_Default_Value) throws Throwable{
		try{
			String option_Text = WithOut_OR_fnsGet_Field_TEXT(DropDown_Xpath).trim();
			assert 	( (option_Text.toLowerCase()).contains(Matching_Default_Value.toLowerCase().trim()) ):
				"FAILED == Default value <"+Matching_Default_Value+"> is not coming into drop down having xpath >>"+DropDown_Xpath+" , please refer screen shot >>Default_Value_Match_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Default value <"+Matching_Default_Value+"> is coming into drop down having xpath >>"+DropDown_Xpath);
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Default_Value_Match_Fail");
			throw new Exception(t.getMessage());
		}
		
	}
	
	
	
//Verify Drop down default value by DD label	
	public void fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab(String DropDown_Label_Xpath, String DropDown_Value_Xpath, String Matching_Default_Value) throws Throwable{
		try{
			String option_Text = fnsGet_Field_TEXT(DropDown_Label_Xpath).trim();
			if( (option_Text.toLowerCase()).contains(Matching_Default_Value.toLowerCase().trim()) ){
				fnsApps_Report_Logs("PASSED == Default value <"+Matching_Default_Value+"> is coming into drop down having xpath >>"+DropDown_Label_Xpath);
			}else{
				fnsApps_Report_Logs("PASSED == Default value <"+Matching_Default_Value+"> is not coming by default, so selecting from drop down having xpath >>"+DropDown_Label_Xpath);
				fnsDD_value_Select_By_MatchingText(DropDown_Label_Xpath, DropDown_Value_Xpath, "li", Matching_Default_Value);
				fnsLoading_Progressingwait(2);
				Default_DD_Value_Check = false;
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("DefaultValue_Match_Select_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	}
		
// Task TAB --- Edit Task and Click on Task Edit button then enter data in Start year, frequency and End year.
	public void fnc_TaskTAB_EditTask_and_Enter_Data_in_StartYear_Frenquency_EndYear(String Edit_Bttn_Xpath, String Popup_Name, String Start_Year, String Frenqency, String End_Year) throws Throwable{
		try{
			fnsGet_Element_Enabled(Edit_Bttn_Xpath);
			fnsWait_and_Click(Edit_Bttn_Xpath);
			
			TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TasksTab_EditMonitorPlanTask_Popup_Tilte"), "Edit Monitor Plan Task", "'"+Popup_Name+"' Popup is not getting opened");
			
			fnsClear_then_Type_for_InputNotWorkingInIE("TasksTab_EditMonitorPlanTask_Popup_StartYear", Start_Year);
			
			fnsClear_then_Type_for_InputNotWorkingInIE("TasksTab_EditMonitorPlanTask_Popup_Frequency", Frenqency);
			
			fnsClear_then_Type_for_InputNotWorkingInIE("TasksTab_EditMonitorPlanTask_Popup_EndYear", End_Year);
			
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_Save_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_Save_Bttn");
			fnsLoading_Progressingwait(3);
			
			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div");
			fnsText_Fetch_and_Assert("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div", "success");
			
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(t.getMessage());
		}
	}
	
//	Testing TAB --- Add and Edit Task and verify dd default values and a new bill code.	
	public void fnc_TestingTAB_AddEditTask_Verify_DDDefaultValue_and_Bill_Code() throws Throwable{
		try{
			
			Default_DD_Value_Check = true;
			
			fnsGet_Element_Enabled("EditAnnualMonitorPlan_TestingTasks_TAB");
			
			//Clicking Testing Tasks TAB
			for(int clicktry=0; clicktry<6; clicktry++){
					fnsWait_and_Click("EditAnnualMonitorPlan_TestingTasks_TAB");
					fnsLoading_Progressingwait(3);
									
					if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("TestingTasksTab_ApplyTaskTemplate_Bttn"))).size()>0){
						break;
					}
					if(clicktry==5){
						isTestCasePass = false;
						fnsTake_Screen_Shot("TabOpenFail");
						throw new Exception("FAILED == <TestingTasks> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
					}
			}
			
			fnsGet_Element_Enabled("Footer");
			fnsGet_Element_Enabled("TestingTasksTab_ApplyTaskTemplate_Bttn");
			fnsWait_and_Click("TestingTasksTab_ApplyTaskTemplate_Bttn");
			fnsLoading_Progressingwait(2);
			
			fnsGet_Element_Enabled("TestingTasksTab_ApplyTaskTemplate_SelectTaskforWorkOrderTemplate_Continue_Bttn");
			fnsWait_and_Click("TestingTasksTab_ApplyTaskTemplate_SelectTaskforWorkOrderTemplate_Continue_Bttn");
			fnsLoading_Progressingwait(2);
			
			//Verify Records are updated into Testing Task list table
			fnsGet_Element_Enabled("TestingTasksTab_TaskList_TableHeader_Xpath");
			Integer TestingTaskListTableRowCount = fnsRetrun_TotalRowCount_OfTable("TestingTasksTab_TaskList_TableHeader_Xpath");
			try{
				assert !(TestingTaskListTableRowCount.equals(0)):"FAILED == TaskList Table update is getting failed, plz see screenshot [TestingTaskListTableUpdateFail" + fnsScreenShot_Date_format() + "]";
			}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("TestingTaskListTableUpdateFail");
				fnsApps_Report_Logs("FAILED == TaskList Table update is getting failed, plz see screenshot [TestingTaskListTableUpdateFail" + fnsScreenShot_Date_format() + "], Getting Exception >> "+t.getMessage());
				throw new Exception("FAILED == TaskList Table update is getting failed, plz see screenshot [TestingTaskListTableUpdateFail" + fnsScreenShot_Date_format() + "], Getting Exception >> "+t.getMessage());}
					
			
			
			fnsGet_Element_Enabled("TestingTaskTab_TaskList_TestingTask_Edit_Bttn");
			fnsWait_and_Click("TestingTaskTab_TaskList_TestingTask_Edit_Bttn");
			
			TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TestingTaskTab_EditMonitorPlanTask_Popup_Tilte"), "Edit Monitor Plan Task", "'"+"Edit Monitor Plan Task -- Testing"+"' Popup is not getting opened");
			
			fnsClear_then_Type_for_InputNotWorkingInIE("TestingTaskTab_EditMonitorPlanTask_Popup_StartYear", (Testing_Task_Details.split(",")[0]) );
			
			fnsClear_then_Type_for_InputNotWorkingInIE("TestingTaskTab_EditMonitorPlanTask_Popup_Frequency", (Testing_Task_Details.split(",")[1]) );
			
			fnsClear_then_Type_for_InputNotWorkingInIE("TestingTaskTab_EditMonitorPlanTask_Popup_EndYear", (Testing_Task_Details.split(",")[2]) );
			

			fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab("TestingTaskTab_EditMonitorPlanTask_Popup_ShipToLoc_DD_Label", "TestingTaskTab_EditMonitorPlanTask_Popup_ShipToLoc_DD_Value", Testing_Task_DD_Default_Value_Match.split(",")[0]);
			fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab("TestingTaskTab_EditMonitorPlanTask_Popup_TestingLoc_DD_Label", "TestingTaskTab_EditMonitorPlanTask_Popup_TestingLoc_DD_Value", Testing_Task_DD_Default_Value_Match.split(",")[1]);
			fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab("TestingTaskTab_EditMonitorPlanTask_Popup_TestCategory_DD_Label", "TestingTaskTab_EditMonitorPlanTask_Popup_TestCategory_DD_Value", Testing_Task_DD_Default_Value_Match.split(",")[2]);
			fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab("TestingTaskTab_EditMonitorPlanTask_Popup_CollectionType_DD_Label", "TestingTaskTab_EditMonitorPlanTask_Popup_CollectionType_DD_Value", Testing_Task_DD_Default_Value_Match.split(",")[3]);
			Thread.sleep(2000);
			fnsLoading_Progressingwait(2);
			fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab("TestingTaskTab_EditMonitorPlanTask_Popup_ListedStd_DD_Label", "TestingTaskTab_EditMonitorPlanTask_Popup_ListedStd_DD_Value", Testing_Task_DD_Default_Value_Match.split(",")[4]);
			fnc_Verify_DD_Defalut_Value_and_Select_correct_value_TestingTab("TestingTaskTab_EditMonitorPlanTask_Popup_AuditorCollection_DD_Label", "TestingTaskTab_EditMonitorPlanTask_Popup_AuditorCollection_DD_Value", Testing_Task_DD_Default_Value_Match.split(",")[5]);
			

			fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_Save_Bttn");
			fnsWait_and_Click("TestingTaskTab_EditMonitorPlanTask_Popup_Save_Bttn");
			fnsLoading_Progressingwait(3);
			

			if( !(Default_DD_Value_Check) ){		
				fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_Confirmation_OK_Bttn");
				fnsWait_and_Click("TestingTaskTab_EditMonitorPlanTask_Popup_Confirmation_OK_Bttn");
				fnsLoading_Progressingwait(3);
			}
			

			
			fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div");
			fnsText_Fetch_and_Assert("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div", "success");
			

			fnVerify_TestingTaskTab_BillingCode_Updated_through_AddSingle_Bttn
			 (0, (Add_BillCode_Data.split(",")[0]), 
					(Add_BillCode_Data.split(",")[1]), 
					(Add_BillCode_Data.split(",")[2]),
					(Add_BillCode_Data.split(",")[3]),
					(Add_BillCode_Data.split(",")[4]));
			
			
			
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}	
	
	
	

	//function to click on EPSF Lookup button
	public void fnsEPSF_LookUp_Bttn_Click(String LookUp_Text_Name) throws Exception {
		boolean LookUpFound=false;
		int columnNumber = 1;
		try{
			fnsGet_Element_Enabled("EPSFFields_LookUp_Data_Grid");
			List<WebElement> TableGrid_objectlists=fnsGet_OR_MtrPlan_ObjectX("EPSFFields_LookUp_Data_Grid").findElements(By.tagName("tr"));
			for(WebElement TableGrid_value:TableGrid_objectlists){
				
				if(TableGrid_value.getText().toLowerCase().contains(LookUp_Text_Name.toLowerCase().trim())){
					String LookUp_Xpath = OR_MtrPlan.getProperty("EPSFFields_LookUp_Data_Grid")+"/tr["+columnNumber+"]/td[2]/div/button";					
					WithOut_OR_fnGet_Element_Enabled(LookUp_Xpath);
					WithOut_OR_fnClick(LookUp_Xpath);
					fnsApps_Report_Logs("PASSED == Successfully click on '"+LookUp_Text_Name+"' Look Up button.");
					fnsLoading_Progressingwait(3);
					LookUpFound=true;
					break;
				}
				columnNumber++;
			}
			if(LookUpFound==false){
				throw new Exception("FAILED == '"+LookUp_Text_Name+"' Look Up is not coming into the EPSF popup, please refer the attached screen shot >> EPSF_LK_Click_Fail"+fnsScreenShot_Date_format());
			}	
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("EPSF_LK_Click_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}

	
	
//########################################################################################################################################################################################
//############################################################   Config Function ##########################################################################################################
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		
		fns_ReportTestResult_atClassLevel(MonitorPlan_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	
	@AfterTest
	public void QuitBrowser(){
		driver.quit();
	}
	
	
}	
	
