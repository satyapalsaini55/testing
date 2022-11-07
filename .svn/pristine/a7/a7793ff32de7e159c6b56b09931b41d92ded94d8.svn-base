package nsf.ecap.New_NSFOnline_Suite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_39_Subway_Dashbord_CA_Report extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}


public Integer CorrectiveActions_View_Total_no_of_Records_Count = 270;
public Integer CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count = 270;


//##########################################   EXCEL Variable ###############################################################################################

public String SelfREV_View_Name = "Self REV";
public String CAR_NO = "";
public BS_03_CheckList BS_03_CheckList = new BS_03_CheckList();
public String ReportName = "Subway REV Extract";
public String Reports_MgmtReports_REV_NC_Round_DD_Value = "2021-1";






@Test( priority = 1)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		fnsApps_Report_Logs("################################## [BS-39] Launch_Browser_and_Successfully_Login_into_the_Application");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}



@Test( priority = 2, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
public void Dashboard_Verify_PicChartCount_for_PreviousAuditRound() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Dashboard_Verify_PicChartCount_for_PreviousAuditRound ");
	try{	
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("Dashboard_SearchCriteria_Button");
		fnsWait_and_Click("Dashboard_SearchCriteria_Button");
		fnsLoading_Progressing_wait(2);
		
		
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("Dashboard_Clear_Button");
		fnsWait_and_Click("Dashboard_Clear_Button");
		fnsLoading_Progressing_wait(2);
		
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("REV Type", "SUBWAY Restaurant Excellence Visit for NSF specialist (SUBREV)");
		
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("REV Round", "2021-1");
		
		
		fnsGet_Element_Enabled("Dashboard_Show_Button");
		fnsWait_and_Click("Dashboard_Show_Button");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Dashboard_AuditScore_Tab");
		fnsWait_and_Click("Dashboard_AuditScore_Tab");
		fnsLoading_Progressing_wait(2);
		
		
		Integer Graph_RecordsCount =  fncVerify_GraphDisplay_And_returnCount();
		
		
		Integer Rev_View_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("REV View ( "+SelfREV_View_Name+" )", "View_Result_Table", 20 );
		
		assert Graph_RecordsCount==Rev_View_Total_no_of_Records_Count:"FAILED == Graph records count ("+Graph_RecordsCount+") are NOT matched with the view records count ("+Rev_View_Total_no_of_Records_Count+").";
		fnsApps_Report_Logs("PASSED == successfully verified that the Graph records count ("+Graph_RecordsCount+") are matched with the view records count ("+Rev_View_Total_no_of_Records_Count+").");
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}


@Test( priority = 3, dependsOnMethods={"Dashboard_Verify_PicChartCount_for_PreviousAuditRound"}, description="")
public void SelfREV_ClickAndOpen_FirstRecord_thenDownloadReport_Verify_forNoError() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::2 SelfREV_ClickAndOpen_FirstRecord_thenDownloadReport_Verify_forNoError ");
	try{	
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("SelfREV_Ajax");
		fnsLoading_Progressing_wait(1);
		fnsLoading_Progressing_wait(1);
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Self REV' Menu Ajax", "Self REV");		
		
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, SelfREV_View_Name, "Yes");
		
		
		String ViewFirstRecord_SelfREVNumber_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Self REV Number")+"]";
		String SelfREVNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_SelfREVNumber_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+SelfREVNumber+"' link", "Default 'SelfREVInfo' Tab", OR_New_NSFOnline.getProperty("checklistInfo_SelfREVInfo_Tab_ByDefault_Opened"));

		
		fnsDownload_File_or_Verify_Validation_Message("Download Report", OR_New_NSFOnline.getProperty("Audits_SelfREVInfo_Tab_DownloadReport_button"));
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}




@Test( priority = 4, dependsOnMethods={"SelfREV_ClickAndOpen_FirstRecord_thenDownloadReport_Verify_forNoError"}, description="")
public void AuditCorrAction_OpenFirstCarFromAdvanceSearch_Submit_and_Approve_It() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::3 AuditCorrAction_OpenFirstCarFromAdvanceSearch_Submit_and_Approve_It ");
	try{	

		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("REVCorrectiveActions_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'REV Corr. Action(s)' Menu Ajax", "REV Corr. Action(s)");
		
		
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		Thread.sleep(1000);
	
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "Pending");
				
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		
		CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
		fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
		
		
		//commented the code on 05-01-2022 as discussion with Ruchi APP-130974, NOM-5896
		
//		if(env.equalsIgnoreCase("test")){
//			CAR_NO = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "CAR-NO")+"]");
//		}else{
//			CAR_NO = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "CAP-NO")+"]");
//		}
//		
		
		// as discussed with itesh it will come only CAR-NO
		CAR_NO = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "CAR-NO")+"]");
		
		BS_03_CheckList.fncAfter_RespondbuttonClik_FillAllTheManadtoryField_and_UploadFile(CAR_NO, 0, "Subway");
		
		
		fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
		fnsWait_and_Click("CA_RespondBttn_Submit_button");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 120, "Corrective Action Submitted Successfully", 25);
		fnsLoading_Progressing_wait(2);			
		
		
		
		fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		Thread.sleep(1000);
		
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "Submitted");
		
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "CAR-NO", CAR_NO);
		
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+CAR_NO+"' link", "//a[contains(text(), '"+CAR_NO+"')]");
		fnsLoading_Progressing_wait(2);
			
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+CAR_NO+"' link", " 'Car Details Respond' screen", OR_New_NSFOnline.getProperty("Audits_CA_CareDtailsRespond_CarStatus"));
		fns_Assert_equalsIgnoreCase_Without_OR("Text", "Status", OR_New_NSFOnline.getProperty("Audits_CA_CareDtailsRespond_CarStatus"), "Submitted");
		
			
		if(CARespond_KendoEditorDisplayed){
			String iframe_Xpath = OR_New_NSFOnline.getProperty("Audit_CA_Approve_Add_Comment_inputBox")+"/preceding::iframe[1]";
			for(int i=1; i<=60; i++){
				try{
					if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					Thread.sleep(1000);
				}
			}
			WebElement iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
			driver.switchTo().frame(iframe_ele);
			String iframeBody_Xpath = "//body[@contenteditable='true']";
			TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation Approved");
			driver.switchTo().defaultContent();
			fnsLoading_Progressing_wait(1);
		}else{
			fnsGet_Element_Enabled("Audit_CA_Approve_Add_Comment_inputBox");
			fnsWait_and_Clear("Audit_CA_Approve_Add_Comment_inputBox");
			fnsWait_and_Type("Audit_CA_Approve_Add_Comment_inputBox", "Automation Approved");  
			fnsLoading_Progressing_wait(1);
		}
		
		
		fnsGet_Element_Enabled("Audit_CA_CareDtailsRespond_Approve_Button");  
		fnsWait_and_Click("Audit_CA_CareDtailsRespond_Approve_Button");
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "Corrective Action Approved Successfully", 25);
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}


@Test( priority = 5, dependsOnMethods={"AuditCorrAction_OpenFirstCarFromAdvanceSearch_Submit_and_Approve_It"}, description="")
public void Verify_and_Download_SubwayRevNC_Report_under_ManagementReports() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::4 Verify_and_Download_SubwayRevNC_Report_under_ManagementReports ");
	try{	
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Reports_Ajax", "Reports_SubMenu_MgmtReports_Ajax");
		
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Reports_MgmtReports_Screen"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Management Reports' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("MgmtReportScreen_Open_Fail");
				throw new Exception("FAILED == 'Management Reports' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> MgmtReportScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Mgmt Reports' Ajax link", "Default 'All Reports' Tab", OR_New_NSFOnline.getProperty("Reports_MgmtReports_All_Reports_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Enabled("View_Result_Table");
		String All_Reports_Table_Text = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").getText().toLowerCase().trim();
		try{
			assert  !(All_Reports_Table_Text.contains("no records found")):"FAILED == Data are not coming under 'All Reports' Tab, please refer the screen shot >> Data_Not_Coming"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Data_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+ReportName+"' report link", "//a[text()='"+ReportName+"']");
		fnsLoading_Progressing_wait(5);
				
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Reports_MgmtReports_REV_NC_Round_DD", Reports_MgmtReports_REV_NC_Round_DD_Value, (10));
		fnsLoading_Progressing_wait(1);
				
		fnsDownload_File("Reports__Generate_Report_Bttn");	
	
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}







public Integer fncVerify_GraphDisplay_And_returnCount() throws Throwable{
	fnsLoading_PageLoad(300);
	fnsLoading_Progressing_wait(2);
	String Graphs_Xpath = "";
	Thread.sleep(3000);
	Integer Graph_RecordCount = 0;
	String Graph_Label_Xpath ="//div[@class='chart-head row']/following::label[@class='ng-binding']";
	int Graph_Label_Ele_Size = driver.findElements(By.xpath(Graph_Label_Xpath)).size();
	boolean Graph_Coming = false;
	try{
		for(int k=1; k<=Graph_Label_Ele_Size; k++){
			String Indv_Graph_Label_Xpath = "(//div[@class='chart-head row']/following::label[@class='ng-binding'])["+k+"]";
			String Indv_GraphLabel = driver.findElement(By.xpath(Indv_Graph_Label_Xpath)).getText().toLowerCase().trim();
			if(Indv_GraphLabel.contains("rev grade summary")){
				Graphs_Xpath = "(//div[@class= 'k-chart' and not(contains(@data-ng-hide, 'chartLoaders'))])["+k+"]";
				Graph_Coming=true;
				break;
			}
			if(k==Graph_Label_Ele_Size && Graph_Coming==false){
				fnsTake_Screen_Shot("Graph_Not_Coming");
				throw new Exception("FAILED == Grpah is not coming, Please refer the screenshot >> Graph_Not_Coming"+fnsScreenShot_Date_format());
			}
		}
		
		
		
		
		List<WebElement>  Graphs_Text_allObjs=TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Graphs_Xpath).findElements(By.tagName("text"));
		
		for(WebElement Graphs_Text_Element:Graphs_Text_allObjs){
			Text_on_Graphs = Graphs_Text_Element.getText().toLowerCase().trim();	
			System.out.println(Text_on_Graphs);
			if( !(Text_on_Graphs.contains("%")) &&  !(Text_on_Graphs.contains("pass"))  &&  !(Text_on_Graphs.contains("fail")) ){
				Graphs_Text_Element.click();				
				break;
			}				
		}	
		
		//Text_on_Graphs = Text_on_Graphs.split("\\(")[0].trim();
		//Text_on_Graphs = Text_on_Graphs.split("\\(")[1].trim(); //Now values coming as (11.12%)
		Graph_RecordCount = Integer.parseInt(Text_on_Graphs);
		fnsApps_Report_Logs("PASSED == successfully clicked on "+Text_on_Graphs+" link on pie chart graph and Count is = "+Graph_RecordCount);
		fnsLoading_Progressing_wait(2);
		return Graph_RecordCount;
		
	}catch(Throwable t) {
		throw new Exception("Pie Chart graph : "+Throwables.getStackTraceAsString(t));
	}

}



//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	try{
		driver.quit();
	}catch(Throwable t){
		//nothing to do			
	}
}

}