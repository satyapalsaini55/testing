package nsf.ecap.TraQtion_Suite;

import java.util.ArrayList;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class Complaint_Management_3 extends TestSuiteBase_TraQtion {

	public String First_Complaint_ID = null;
	public String Second_Complaint_ID = null;
	public String Thrid_Complaint_ID = null;

@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}



//##########################################   EXCEL Variable ###############################################################################################




@Test( priority = 1)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{		
		fnsApps_Report_Logs("################################## [BS-03]  Complaint Management Test");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsTraQtionPortal_LaunchBrowserAndLogin("TraQtion Portal", Login_UserName, Login_Password);			
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 2, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
public void First_Complaint_Generation() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 First_Complaint_Generation ");
	try{
		fnsGet_Element_Enabled("TraQtionPortal_TraQtion_Button");
		fnsWait_and_Click("TraQtionPortal_TraQtion_Button");					
		
		for(int a=0; a<=120; a++){ 
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			Integer tabsCount = tabs.size();
			if(tabsCount==2){
				for (int i = 0; i < tabsCount; i++) {
					if( !( (TraQtionPortal_Window.equalsIgnoreCase(tabs.get(i))) ) ){
						driver.switchTo().window(tabs.get(i));
						fnsApps_Report_Logs("PASSED == Successfully Switch to 'TraQtion' Window, jump from 'TraQtion Portal' by clicking on TraQtion button.");
						for(int l=1; l<=120; l++){
							if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_Acknowledge_bttn"))).size()>0){
								fnsGet_Element_Enabled("TraQtionPortal_Acknowledge_bttn");
								Thread.sleep(2000);
								fnsWait_and_Click("TraQtionPortal_Acknowledge_bttn");
								Thread.sleep(1000);
								break;
							}
							if(l==120){
								fnsTake_Screen_Shot("Acknowledge_Alert_Not_Coming");
								throw new Exception("FAILED == Acknowledge alert is not coming, after 120 Seconds wait, please refer the screen shot >> Acknowledge_Alert_Not_Coming"+fnsScreenShot_Date_format());
							}
						}
						
						break;
					}
				}
				break;
			}else{
				Thread.sleep(1000);
			}
			if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
				fnsTake_Screen_Shot("Switch_TraQtion_WindowFail");
				throw new Exception("FAILED == 'TraQtion' window is not getting open  by clicking on TraQtion button, after 120 Seconds wait, please refer the screen shot >> Switch_TraQtion_WindowFail"+fnsScreenShot_Date_format());
			}
		}
		
		
		First_Complaint_ID = fnc_Create_Complaint("First");
				
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 3, dependsOnMethods={"First_Complaint_Generation"}, description="")
public void First_Complaint_SearchOpen_then_MarkedJustifiedAsYes_and_SaveComplaint_TraverseAllTheTabsToVerifyAnyError() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("########### Test Case ::2 First_Complaint_SearchOpen_then_MarkedJustifiedAsYes_and_SaveComplaint_TraverseAllTheTabsToVerifyAnyError ");
	try{
		fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("Search Complaint", OR_TraQtion.getProperty("TraQtion_SearchComplaint_Ajax") );
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Search Complaint' Menu link", "Search Complaint", OR_TraQtion.getProperty("TraQtion_SearchComplaint_ComplaintID"));
		
		fnsGet_Element_Enabled("TraQtion_SearchComplaint_ComplaintID");
		fnsWait_and_Type("TraQtion_SearchComplaint_ComplaintID", First_Complaint_ID);
		
		fnsGet_Element_Enabled("TraQtion_Search_Bttn");
		fnsWait_and_Click("TraQtion_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);		
		
		fnsGet_Element_Enabled("TraQtion_Search_FirstRow_FirstColumn_Link");
		fnsGet_Field_TEXT("TraQtion_Search_FirstRow_FirstColumn_Link");
		String Search_Result_Value = fnsGet_Field_TEXT("TraQtion_Search_FirstRow_FirstColumn_Link");
		try{
			assert (Search_Result_Value.toLowerCase().trim().equals(First_Complaint_ID.toLowerCase()));
		}catch(Throwable t){
			fnsTake_Screen_Shot("Search_Complaint_Not_Working");
			throw new Exception("FAILED == Incorrect '"+Search_Result_Value+"' search result is displayed on Search Complaint screen [Correct value was = "+First_Complaint_ID+" ], please refer the screen shot >> Search_Complaint_Not_Working"+fnsScreenShot_Date_format());
		}
		
		fnsWait_and_Click("TraQtion_Search_FirstRow_FirstColumn_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'"+First_Complaint_ID+"' link", "View Complaint", OR_TraQtion.getProperty("TraQtion_EditComplaint_Info_Tab"));
		
		fnc_Complaint_infoTab_Marked_Justified_Yes_and_Save(First_Complaint_ID);
		
		
		fnsGet_Element_Enabled("TraQtion_EditComplaint_Documents_Tab");
		fnsWait_and_Click("TraQtion_EditComplaint_Documents_Tab");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);	
		fnsGet_Element_Enabled("TraQtion_EditComplaint_AuditTrail_Tab");
		fnsWait_and_Click("TraQtion_EditComplaint_AuditTrail_Tab");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_EditComplaint_Notifications_Tab");
		fnsWait_and_Click("TraQtion_EditComplaint_Notifications_Tab");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_EditComplaint_RequestResponse_Tab");
		fnsWait_and_Click("TraQtion_EditComplaint_RequestResponse_Tab");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_EditComplaint_Snapshot_Tab");
		fnsWait_and_Click("TraQtion_EditComplaint_Snapshot_Tab");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 4, dependsOnMethods={"First_Complaint_SearchOpen_then_MarkedJustifiedAsYes_and_SaveComplaint_TraverseAllTheTabsToVerifyAnyError"}, description="")
public void Second_Complaint_Generation() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::3 Second_Complaint_Generation ");
	try{
		Second_Complaint_ID = fnc_Create_Complaint("Second");	
		fnc_Complaint_infoTab_Marked_Justified_Yes_and_Save(Second_Complaint_ID);
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



@Test( priority = 5, dependsOnMethods={"Second_Complaint_Generation"}, description="")
public void Thrid_Complaint_Generation() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::4 Thrid_Complaint_Generation ");
	try{
		Thrid_Complaint_ID = fnc_Create_Complaint("Thrid");
		fnc_Complaint_infoTab_Marked_Justified_Yes_and_Save(Thrid_Complaint_ID);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}


@Test( priority = 6, dependsOnMethods={"Thrid_Complaint_Generation"}, description="")
public void Escalation_VerifyAllThreeComplaintsExistsIntoTheEscalation_SetLiabilityToUnjustified_then_VerifyAllComplaintsClosed() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################ Test Case ::5 Escalation_VerifyAllThreeComplaintsExistsIntoTheEscalation_SetLiabilityToUnjustified_then_VerifyAllComplaintsClosed ");
	try{
		try{
			for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
				try{
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_ViewComplaint_EscalationID_link"))).size()>0){
						fnsGet_Element_Enabled("TraQtion_ViewComplaint_EscalationID_link");
						fnsApps_Report_Logs("PASSED == < "+fnsGet_Field_TEXT("TraQtion_ViewComplaint_EscalationID_link")+" > Escalation ID is generated successfully.");
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable tt){
					Thread.sleep(1000);
				}
				if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
					throw new Exception("FAILED == Escalation ID is NOT getting generate, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> Escalation_ID_Generate_FAIL"+fnsScreenShot_Date_format());
				}
			}
			}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("Escalation_ID_Generate_FAIL");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		String Escalation_ID = fnsGet_Field_TEXT("TraQtion_ViewComplaint_EscalationID_link")	;
		fnsWait_and_Click("TraQtion_ViewComplaint_EscalationID_link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Escalation ID' link", "View Escalation", "//div[contains(text(), 'View Escalation')]");
		
		String Related_Table_Data = fnsGet_Field_TEXT("TraQtion_EditEscalation_RelatedComplaint_div");
		
		try{
			assert (Related_Table_Data.toLowerCase().trim().contains(First_Complaint_ID.toLowerCase()));
		}catch(Throwable t){
			fnsTake_Screen_Shot(First_Complaint_ID+"_ComplaintID_Not_Exists");
			throw new Exception("FAILED == "+First_Complaint_ID+" Complaint ID is Not exists into the escalation - "+Escalation_ID+", please refer the screen shot >> "+First_Complaint_ID+"_ComplaintID_Not_Exists"+fnsScreenShot_Date_format());
		}
		
		
		try{
			assert (Related_Table_Data.toLowerCase().trim().contains(Second_Complaint_ID.toLowerCase()));
		}catch(Throwable t){
			fnsTake_Screen_Shot(Second_Complaint_ID+"_ComplaintID_Not_Exists");
			throw new Exception("FAILED == "+Second_Complaint_ID+" Complaint ID is Not exists into the escalation - "+Escalation_ID+", please refer the screen shot >> "+Second_Complaint_ID+"_ComplaintID_Not_Exists"+fnsScreenShot_Date_format());
		}
		
		
		try{
			assert (Related_Table_Data.toLowerCase().trim().contains(Thrid_Complaint_ID.toLowerCase()));
		}catch(Throwable t){
			fnsTake_Screen_Shot(Thrid_Complaint_ID+"_ComplaintID_Not_Exists");
			throw new Exception("FAILED == "+Thrid_Complaint_ID+" Complaint ID is Not exists into the escalation - "+Escalation_ID+", please refer the screen shot >> "+Thrid_Complaint_ID+"_ComplaintID_Not_Exists"+fnsScreenShot_Date_format());
		}
		
		
		fnsApps_Report_Logs("PASSED == Successfully verified that all the three complaints id are exists into the escalation.");
		
		
		if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_EditComplaint_Info_Tab"))).size()>0){
			fnsGet_Element_Enabled("TraQtion_EditComplaint_Info_Tab");
			fnsWait_and_Click("TraQtion_EditComplaint_Info_Tab");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}
		
		fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
		fnsWait_and_Click("TraQtion_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditEscalation_Description");
		fnsWait_and_Type("TraQtion_EditEscalation_Description", "Automation - Escalation Test >> "+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yyyy"));
		
		
		fnsGet_Element_Enabled("TraQtion_EditEscalation_Liability_UnJustified_Radio_bttn");
		fnsWait_and_Click("TraQtion_EditEscalation_Liability_UnJustified_Radio_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		fnsGet_Element_Enabled("TraQtion_EditEscalation_LiabilityReason");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		fnsWait_and_Type("TraQtion_EditEscalation_LiabilityReason", "Automation >> Close Escalation");
		
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditEscalation_Status_Confirmation_YES_bttn");
		fnsWait_and_Click("TraQtion_EditEscalation_Status_Confirmation_YES_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		fnsVerify_Validation_Message_TraQtion();	
		
		fnsGet_Element_Enabled("TraQtion_EditEscalation_RelatedComplaint_div");
		Related_Table_Data = fnsGet_Field_TEXT("TraQtion_EditEscalation_RelatedComplaint_div");
		
		try{
			assert ( !(Related_Table_Data.toLowerCase().trim().contains("open")) );
			fnsApps_Report_Logs("PASSED == The status of all the complaints got change into CLOSED from open status.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Complaint_Closed_status_Fail");
			throw new Exception("FAILED == after closing the Escalation, complaint still exists into the OPEN status, please refer the screen shot >> Complaint_Closed_status_Fail"+fnsScreenShot_Date_format());
		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



public String fnc_Create_Complaint(String Complaint_seq) throws Throwable{
	try{
		fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("Create Complaint", OR_TraQtion.getProperty("TraQtion_CreateComplaint_Ajax") );
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Create Complaint' Menu link", "Create Complaint", "//div[contains(text(), 'Create Complaint')]");
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_LocationName_LK_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_LocationName_LK_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_LocationName_LK_Text");
		fnsWait_and_Type("TraQtion_CreateComplaint_Complaint_LocationName_LK_Text", "S0364019");
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_CreateComplaint_Complaint_HowReceived_DD_Click", "TraQtion_CreateComplaint_Complaint_HowReceived_DD_List", "li", "Email");
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_CreateComplaint_Complaint_ComplaintType_DD_Click", "TraQtion_CreateComplaint_Complaint_ComplaintType_DD_List", "li", "Product Quality");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_CreateComplaint_Complaint_ComplaintSubtype_DD_Click", "TraQtion_CreateComplaint_Complaint_ComplaintSubtype_DD_List", "li", "Color");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		if(fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateComplaint_Complaint_ProductCategory_LK_input").getAttribute("value").trim().length()==0){
			fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_ProductCategory_LK_bttn");	
			fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_ProductCategory_LK_bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("LookUp_first_Radio_Bttn");
			fnsWait_and_Click("LookUp_first_Radio_Bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}
		
		if(fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateComplaint_Complaint_Product_LK_input").getAttribute("value").trim().length()==0){
			fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_Product_LK_bttn");
			fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_Product_LK_bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("LookUp_first_Radio_Bttn");
			fnsWait_and_Click("LookUp_first_Radio_Bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}
		Thread.sleep(2000);
		if(fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateComplaint_Complaint_Supplier_LK_input").getAttribute("value").trim().length()==0){
			fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_Supplier_LK_bttn");
			fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_Supplier_LK_bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("LookUp_first_Radio_Bttn");
			fnsWait_and_Click("LookUp_first_Radio_Bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}
		
		Actions act2 = new Actions(driver);				
		act2.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(1500);
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_NEXT_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_NEXT_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Next' button", "Product Details TAB", OR_TraQtion.getProperty("TraQtion_CreateComplaint_ProductDetails_DeliveryDate"));
		
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_ProductDetails_ProductLabelInformation");
		fnsWait_and_Type("TraQtion_CreateComplaint_ProductDetails_ProductLabelInformation", "Automation [BS-03] Complaint Management Test - "+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yyyy"));
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_ProductDetails_DeliveryDate");
		fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateComplaint_ProductDetails_DeliveryDate").clear();
		fnsWait_and_Type("TraQtion_CreateComplaint_ProductDetails_DeliveryDate", TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yyyy"));
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_ProductDetails_NumberOfCasesAffected");
		fnsWait_and_Type("TraQtion_CreateComplaint_ProductDetails_NumberOfCasesAffected", "2");
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_ProductDetails_Invoice");
		fnsWait_and_Type("TraQtion_CreateComplaint_ProductDetails_Invoice", TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("ddMMMyyyy"));
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_ProductDetails_NumberOfCasesDelivered");
		fnsWait_and_Type("TraQtion_CreateComplaint_ProductDetails_NumberOfCasesDelivered", "3");
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_ProductDetails_AreYouRequestingCredit_NO_Radio_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_ProductDetails_AreYouRequestingCredit_NO_Radio_bttn");
		
		act2.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(1500);
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_NEXT_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_NEXT_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Next' button", "Documents TAB", OR_TraQtion.getProperty("TraQtion_CreateComplaint_Documents_AddAttachment_bttn"));
		
	//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Documents_AddAttachment_bttn");
	//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("TraQtion_CreateComplaint_Documents_AddAttachment_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Next' button", "Documents TAB", OR_TraQtion.getProperty("TraQtion_CreateComplaint_Documents_AddAttachment_bttn"));
	//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
		WebElement Browser = fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateComplaint_Documents_AddAttachment_Browse");
		Browser.sendKeys(FileUploadPath);
		
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_CreateComplaint_Documents_AddAttachment_BrowseFile_Progress"))).size()>0){
				Thread.sleep(1000);
			}else{
				Thread.sleep(1000);
			//	fnsApps_Report_Logs("PASSED == Document is Successfully Uploaded.");
				break;
			}
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				fnsTake_Screen_Shot("FileUploadFail");
				throw new Exception("FAILED == 'Document' TAB : File is not getting Upload, TimeOut after <"+NewNsfOnline_Element_Max_Wait_Time+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
			}
		}
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_CreateComplaint_Documents_AddAttachment_BrowseFile_Progress"))).size()>0){
				Thread.sleep(1000);
			}else{
				Thread.sleep(1000);
				fnsApps_Report_Logs("PASSED == Document is Successfully Uploaded.");
				break;
			}
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				fnsTake_Screen_Shot("FileUploadFail");
				throw new Exception("FAILED == 'Document' TAB : File is not getting Upload, TimeOut after <"+NewNsfOnline_Element_Max_Wait_Time+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
			}
		}
	//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsWait_and_Type("TraQtion_CreateComplaint_Documents_AddAttachment_TextArea", "Test Automation");
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Documents_AddAttachment_OK_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Documents_AddAttachment_OK_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Documents_DocumentDetails_Table");
		
		
		for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
			try{
				String DocumentDetails_Table_Text = fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateComplaint_Documents_DocumentDetails_Table").getText().toLowerCase().trim();
				assert ( !(DocumentDetails_Table_Text.contains("no records found")) ) :
					"FAILED == New record is not displayed into the 'Document details' section under Document TAB, please refer the screen shot >> Record_Not_Added"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == new record is successfully added into the 'Document details' section under Document TAB.");
				isTestCasePass = true;
				break;
			}catch(Throwable t){
				if(i==NewNsfOnline_Element_Max_Wait_Time){
					fnsTake_Screen_Shot("Record_Not_Added");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					Thread.sleep(1000);
				}
			}
		}
		
		act2.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(1500);
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Complaint_NEXT_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Complaint_NEXT_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Next' button", "Confirmation TAB", OR_TraQtion.getProperty("TraQtion_CreateComplaint_Confirmation_Submit_bttn"));
		
		
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Confirmation_Submit_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Confirmation_Submit_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Submit' button", "ComplaintSubmitConfirmation Popup", OR_TraQtion.getProperty("TraQtion_CreateComplaint_Confirmation_ComplaintSubmitConfirmation_title"));
		fnsGet_Element_Enabled("TraQtion_CreateComplaint_Confirmation_ComplaintSubmitConfirmation_Yes_bttn");
		fnsWait_and_Click("TraQtion_CreateComplaint_Confirmation_ComplaintSubmitConfirmation_Yes_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("Popup 'Yes' button", "Complaint ID generate message", OR_TraQtion.getProperty("TraQtion_CreateComplaint_Success_Message_Div"));
				
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		String ComplaintID = fnsGet_Field_TEXT("TraQtion_Message_Div").trim();
		
		try{
			assert ( (ComplaintID.toLowerCase().contains("success")) ) :
				"FAILED == "+Complaint_seq+" Complaint ID is not generated, please refer the screen shot >> "+Complaint_seq+"_ComplaintID_Generate_FAIL"+fnsScreenShot_Date_format();
			ComplaintID = ComplaintID.split("Complaint")[0].trim();			
			fnsApps_Report_Logs("###########  PASSED == "+Complaint_seq+" Complaint ID <"+ComplaintID+"> is generated Successfully. ####### ");
		}catch(Throwable t){
			fnsTake_Screen_Shot(Complaint_seq+"_ComplaintID_Generate_FAIL");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	return ComplaintID;
	}catch(Throwable t){
		throw new Exception (Complaint_seq+ Throwables.getStackTraceAsString(t));
	}
}


public void fnc_Complaint_infoTab_Marked_Justified_Yes_and_Save(String Complaint_ID) throws Throwable{
	try{
		fnsGet_Element_Enabled("TraQtion_EditComplaint_Info_Tab");
		fnsWait_and_Click("TraQtion_EditComplaint_Info_Tab");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
		fnsWait_and_Click("TraQtion_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		fnsGet_Element_Enabled("TraQtion_EditComplaint_InfoTab_Justified_Yes_Radio_bttn");
		fnsWait_and_Click("TraQtion_EditComplaint_InfoTab_Justified_Yes_Radio_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditComplaint_InfoTab_JustifyUnjustifyReason");
		fnsWait_and_Type("TraQtion_EditComplaint_InfoTab_JustifyUnjustifyReason", "Automation Test");
		
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsVerify_Validation_Message_TraQtion();	
		
		
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}



//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(TraQtion_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}