package nsf.ecap.Client_Suite;

import nsf.ecap.util.*;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class SearchClient extends TestSuiteBase_CLNT{
	
	public static boolean IsBrowserPresentAlready = false;
	public static String Errormsg;
	public static Integer RowCountAfterUpdate;
	public static Integer RowCountBeforeUpdate;
	
	
	public static Integer count = -1;
	public static Integer RowCount;
	public static boolean skip = true;
	public static String ScenarioName = null;
	public static String CaseNo = null;
	public static String ScenaioName = null;
	public static String ORG_Unit = null;
	public static String Client_22490 = null;
	public static String ISR_Client_ID = null;
	
	

	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		isTestCasePass=true;
		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
	
			

			if (!TestUtil.isTestCaseRunnable(Client_Suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");}

			runmodes = TestUtil.getDataSetRunmodes(Client_Suitexls, className);	
		
			fnsApps_Report_Logs("=========================================================================================================================================");
		
		}catch(SkipException sk){
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}catch (Throwable t) {								
				fnsTake_Screen_Shot(className);				
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = t.getMessage();
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );
		}
}
	
	
	
		
@Test(dataProvider = "getTestData", priority = 1)
public void Search_Edit_and_Update_Client_For_(String Case_No, String Scenario_Name) throws Throwable{	
	
	fnsApps_Report_Logs("=========================================================================================================================================");
	String emailTempDD="";		
	count++;
			
	ScenaioName = Scenario_Name.trim();
	ORG_Unit = (Scenario_Name.split("\\_")[0]).trim();
	Client_22490 = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorpFacility_LookUp").split("\\,")[0].trim();
	
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
				IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
				fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
			}
			
			
			
			if(ORG_Unit.contains("PLUMB")){
				emailTempDD= fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EmailTemplate_DD");
				Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType();
				Search_Client_BySearchCriteria_CorpFacility_and_Verify_Client_in_SearchResultTable_and_Click_on_ClientLink();
			}		
			
			
			if(ORG_Unit.contains("ISR")){
				emailTempDD= fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "EmailTemplate_DD_ISRUK");
				ISR_Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType_and_Click_on_FirstClient();
			}
			
			
			
			 String uniqueNum=getUniqueDateTime();
			ContactsTAB_EditContactTAB_FillMandatoryData_in_ADDContactPopup_and_VerifyRecordsUpdated(uniqueNum);
			fnsLoading_Progressingwait(1);
			ContactsTAB_ClickOnEditContactBttn_Enter_PhoneNumber_and_VerifyRecordsUpdated(uniqueNum);
			fnsLoading_Progressingwait(1);
			ContactsTAB_ClickOnDeleteBttn_and_VerifyRecords_are_Deleted_from_Table(uniqueNum);
			fnsLoading_Progressingwait(1);
			FacilitiesTAB_ClickOnCode_ExpandTheViewClient_ClickOnCorporateParent();
			fnsLoading_Progressingwait(1);
			CorrespondenceTAB_EditCorrespondence_FillMandatoryData_in_ADDCorrespondencePopup_and_VerifyRecordsUpdated(emailTempDD);
			fnsLoading_Progressingwait(1);

			
			if(ORG_Unit.contains("PLUMB")){
				LinksTAB_Edit_ClickOnAddNewBttn_FillMandatoryData_and_SaveIt();
	
				LinksTAB_Edit_ClickOnEditBttn_and_SaveIt_Then_Delete_the_Link();
				fnsLoading_Progressingwait(2);
				
				BillingInfoTAB_Edit_ClickOnAddBillingInfoBttn_FillData_in_PopUp_and_SaveIt();
			}
			
			//DB Query
			fnsDeleteQuery_for_CorrespondenceTAB_in_EditClient();
			
		}
	}catch(SkipException sk){
			throw new SkipException(sk.getMessage());
			
		
	}catch(Throwable t){
		fail = true;
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}						
	
}


//##################################################################################################################################################################################################
//###########################################################       Class methods     ##############################################################################################################	
	

public void ISR_Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType_and_Click_on_FirstClient() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("#################### Test Case ::::::1 ISR_Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType_and_Click_on_FirstClient ");
	
	try{
		fncVerify_ClickonAjax_then_SearchCriteria_OrgUnitAs_ListStatus_ClientType("ISRUK - ISR - United Kingdom");
		
		
		//Commented as ORG and List status dd functionality has been changed. 10.8.2017
		/*fnSearchClient_Ajax_Link_Click();
			
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("Footer");
		Thread.sleep(1000);
		fnsGet_Element_Enabled("SearchClient_Search_Bttn");
		Thread.sleep(500);
		
		fnsMove_To_Element_and_Click("SearchClient_ClientType");
		fnsWait_and_Click("SearchClient_ClientType");
		Thread.sleep(1000);
		
		fnsWait_and_Click("SearchClient_ListStatus");
		Thread.sleep(1000);	
	
		fnsWait_and_Click("SearchClient_OrgUnit_ISRIND");
		Thread.sleep(1000);
				
		fnsWait_and_Click("SearchClient_Search_Bttn");
		fnsLoading_Progressingwait(1);
		*/
		
		
		//For ISR client, 11Apr2K19
		//fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable("SearchClient_TableHeaderXpath","SearchClient_TableCellHeaderXpath","CO","ISR - United Kingdom","APPLY");
		//ISR_Client_ID=fnsGet_Field_TEXT("SearchClient_Table_1st_Cell").trim();
		
		//Getting Last_Client_ID for ISR, and clicking and Adding row in Last Client ID.11Apr2K19
		int lastRow=fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable_GetLastRowNumForISR("SearchClient_TableHeaderXpath","SearchClient_TableCellHeaderXpath","CO","ISR - United Kingdom","APPLY");
		String SearchClient_Table_last_Cell=".//*[@id='mainForm:stdSearchC_data']/tr["+lastRow+"]/td[1]";
		ISR_Client_ID=fnsGet_Field_TEXT_ForISR(SearchClient_Table_last_Cell).trim();
		
		
		if(ISR_Client_ID.toLowerCase().contains("no records found")){
			fnsTake_Screen_Shot("ISR_Client_ID_Not_Found");
			throw new Exception ("FAILED == There are no records found for applied filter, please refer screen shot ["+ "ISR_Client_ID_Not_Found" + fnsScreenShot_Date_format() + "]");
		}
			Thread.sleep(1500);
		fnsTable_ClickOn_LINK_ByMatchingText(ISR_Client_ID);
		fnsLoading_Progressingwait(1);
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Search_Client Screen : "+Throwables.getStackTraceAsString(t));
	}
}
	


	
//@Test(priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="  [Menu>Client>Search Client. Mention the following: Org unit as :Plumbing and Related Products. List Status :APPLY Client type: CO Click on Search button and verify that Org unit,Type and list status are displayed as selected.]")
public void Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");		
	fnsApps_Report_Logs("###################################################### Test Case ::::::1 Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType ");
	
	try{
		
		fncVerify_ClickonAjax_then_SearchCriteria_OrgUnitAs_ListStatus_ClientType("ORGU0045 - Treatment Chemicals and Media");
		
		
		
		/*//Commented as ORG and List status dd functionality has been changed. 10.8.2017
		fnSearchClient_Ajax_Link_Click();
			
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("Footer");
		Thread.sleep(1000);
		fnsGet_Element_Enabled("SearchClient_Search_Bttn");
		Thread.sleep(500);
		
		fnsMove_To_Element_and_Click("SearchClient_ClientType");
		fnsWait_and_Click("SearchClient_ClientType");
		Thread.sleep(1000);
		
		fnsWait_and_Click("SearchClient_ListStatus");
		Thread.sleep(1000);	
	
		fnsWait_and_Click("SearchClient_OrgUnit_PLUMB");
		Thread.sleep(1000);
		
		fnsWait_and_Click("SearchClient_Search_Bttn");
		fnsLoading_Progressingwait(1);*/
		
		
		
		
		fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable("SearchClient_TableHeaderXpath","SearchClient_TableCellHeaderXpath","CO","Treatment Chemicals and Media","APPLY");
	
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Search_Client Screen : "+Throwables.getStackTraceAsString(t));
	}
}
	
	
	
//@Test(dependsOnMethods={"Search_Client_BySearchCriteria_OrgUnitAs_ListStatus_ClientType"}, priority = 2, description="  [Click on Clear link. Search the Client -22490 using Corp/Facility # using the lookup help button. Click on Search button.]")
public void Search_Client_BySearchCriteria_CorpFacility_and_Verify_Client_in_SearchResultTable_and_Click_on_ClientLink() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("########### Test Case ::::::2 Search_Client_BySearchCriteria_CorpFacility_and_Verify_Client_in_SearchResultTable_and_Click_on_ClientLink ");
	
	try{
		fnsGet_Element_Enabled("SearchClient_Clear_Bttn");
		fnsWait_and_Click("SearchClient_Clear_Bttn");
		
		fnsLoading_Progressingwait(1);
		//Thread.sleep(3000);
		//fnsLoading_Progressingwait(1);
		
		
		try{
			for(int LkpClickCount=0; LkpClickCount<3; LkpClickCount++){
				if(driver.findElements(By.xpath(OR_CLNT.getProperty("SearchClient_CorpFacility_LookUp_Bttn"))).size()>0){
					fnsLoading_Progressingwait(1);
					driver.findElement(By.xpath(OR_CLNT.getProperty("SearchClient_CorpFacility_LookUp_Bttn"))).click();
					fnsLoading_Progressingwait(1);
				}
				for(int wait=0; wait<30; wait++){
					if(driver.findElements(By.xpath(OR_CLNT.getProperty("SearchClient_CorpFacility_LookUp_ClientSearch_Field"))).size()>0){
						Thread.sleep(2000);
						break;
					}else{
						Thread.sleep(1000);
					}
				}
				if(driver.findElements(By.xpath(OR_CLNT.getProperty("SearchClient_CorpFacility_LookUp_ClientSearch_Field"))).size()>0){
					break;
				}
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("SearchClient_CorpFacility_LookUp_Bttn");
			fnsApps_Report_Logs("FAILED == Unable to click on SearchClient_CorpFacility_LookUp_Bttn,Please refer ScreenShot [ "+"SearchClient_CorpFacility_LookUp_Bttn"+fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+t.getMessage());
			throw new Exception("FAILED == Unable to click on SearchClient_CorpFacility_LookUp_Bttn,Please refer ScreenShot [ "+"SearchClient_CorpFacility_LookUp_Bttn"+fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+t.getMessage());}
	
		
		fnsGet_Element_Enabled("SearchClient_CorpFacility_LookUp_ClientSearch_Field");
		fnsWait_and_Type("SearchClient_CorpFacility_LookUp_ClientSearch_Field", Client_22490);
		fnsWait_and_Type("SearchClient_CorpFacility_LookUp_ClientNameSearch_Field", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorpFacility_LookUp").split("\\,")[1].trim());
			
		fnsLookup_RadioBttn_Select();
		
		fnsGet_Element_Enabled("SearchClient_Search_Bttn");
		//fnsLoading_Progressingwait(1);
		
		
		//Added as clickin on 22490 is getting fail //18.5.2017
		fnsWait_and_Type("SearchClient_CorpFacilityName_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorpFacility_LookUp").split("\\,")[1].trim());
		
		
		
		fnsWait_and_Click("SearchClient_Search_Bttn");
		fnsLoading_Progressingwait(1);
		
		fnsGet_Element_Enabled("SearchClient_Table_CorpFac_Filter");
		
	
		try{
			String GetText=fnsGet_Field_TEXT("SearchClient_Table_1st_Cell").trim();	
			//fnsApps_Report_Logs("GETTEXT   >>> "+GetText);
			assert GetText.contains(Client_22490):"FAILED == Text ("+Client_22490+") is not matched with the text fetched from element having xpath >>"+"SearchClient_Table_1st_Cell";
			fnsApps_Report_Logs("PASSED ==  Text ("+Client_22490+") is matched with the text fetched from element having xpath >>"+"SearchClient_Table_1st_Cell");
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("SearchClient_Table_1st_Cell");
			fnsApps_Report_Logs(", Getting Exception >> "+t.getMessage());
			throw new Exception(", Getting Exception >> "+t.getMessage());
		}
		
		fnsTable_ClickOn_LINK_ByMatchingText(Client_22490);
		fnsLoading_Progressingwait(1);
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Search_Client Screen : "+Throwables.getStackTraceAsString(t));
	}
}	

//@Test(dependsOnMethods={"Search_Client_BySearchCriteria_CorpFacility_and_Verify_Client_in_SearchResultTable"}, priority = 3, description="  [Click on the Client#: Go to the Contacts tab and click on Edit: Click on Add contact button and fill in the necessary information]")
	public void ContactsTAB_EditContactTAB_FillMandatoryData_in_ADDContactPopup_and_VerifyRecordsUpdated(String uniqueCode) throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("########### Test Case ::::::3 ContactsTAB_EditContactTAB_FillMandatoryData_in_ADDContactPopup_and_VerifyRecordsUpdated ");
	
	try{
		fnsGet_Element_Enabled("Footer");
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("ViewClient_Contacts_Tab");
		fnsWait_and_Click("ViewClient_Contacts_Tab");
		fnsLoading_Progressingwait(1);
			
		//Clicking on Edit button on view contact page.
		for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
			if(driver.findElements(By.xpath(OR_CLNT.getProperty("ViewClient_Contacts_ClientContact_Table_1st_Cell"))).size()>0){
				fnsWait_and_Click("ViewClient_Edit_Bttn");
				fnsLoading_Progressingwait(1);
				break;}
			else{Thread.sleep(1000);}
		}
		
		Thread.sleep(4000);
		RowCountBeforeUpdate=fnsTable_Total_RowCount_Fetch("EditClient_ClientContacts_TableHeaderXpath");
		//System.out.println("RowCountBeforeUpdate  " +RowCountBeforeUpdate);
		
		fnsGet_Element_Enabled("EditClient_Contacts_AddContact_Bttn");
		fnsWait_and_Click("EditClient_Contacts_AddContact_Bttn");
		
		fnsGet_Element_Enabled("EditClient_Contacts_AddContact_Popup_FirstName");
		fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_FirstName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FirstName"));
		
		fnsLoading_Progressingwait(1);
		//fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_LastName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName")); //"AutoLName"+uniqueCode
		fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_LastName", "AutoLName"+uniqueCode);
		fnsLoading_Progressingwait(1);
		fnsDD_value_Select_By_MatchingText("EditClient_Contacts_AddContact_Popup_Type_DD_Click", "EditClient_Contacts_AddContact_Popup_Type_DD_Value", "li", /*"CONT"*/fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Type_DD"));
		fnsLoading_Progressingwait(1);
		fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_Email", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email_From"));
		
		//Comment this line as it is mandatory, but coming as already filled.
		//fnsDD_value_Select_By_MatchingText("EditClient_Contacts_AddContact_Popup_Type_DD_Click", "EditClient_Contacts_AddContact_Popup_Type_DD_Value", "li", Client_Suitexls.getCellData("Search_CLNT", 2, 6));
		fnsLoading_Progressingwait(1);
		//Added new Lines as fields are become mandatory
		fnsDD_value_Select_By_MatchingText("EditClient_Contacts_AddContact_Popup_State_DD_Click", "EditClient_Contacts_AddContact_Popup_State_DD_Value", "li", "Alaska");
		fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_Postal", fnsReturn_Requried_cal("ddMMyyyy"));
		
		
		fnsLoading_Progressingwait(1);
		fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_AddressLine1", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddressLine1"));
		fnsLoading_Progressingwait(1);
		fnsDD_value_Select_By_MatchingText("EditClient_Contacts_AddContact_Popup_BillToOffice_DD_Click", "EditClient_Contacts_AddContact_Popup_BillToOffice_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BillToOffice_DD"));
		fnsLoading_Progressingwait(1);
		fnsWait_and_Click("EditClient_Contacts_AddContact_Popup_SaveandClose_Bttn");
		
		fnsLoading_Progressingwait(1);
		
		
	
		// Commented as not working for we are sorry
		/*TestSuiteBase_Aspirago.fnsLoading_Progressingwait(1);(); 
		//Verify Popup Close without any error.		
		try{
			
			if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_Contacts_AddContact_Bttn"))).size()>0){
				fnsApps_Report_Logs("PASSED == Popup is successfully updated.");
			}else{
				Errormsg=fnsGet_Field_TEXT("EditClient_Contacts_AddContact_Popup_ErrorMsg_Div");
				assert !(Errormsg.contains("mandatory"));
			}
		}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("PopupDisplayedError");
				fnsApps_Report_Logs("FAILED == Popup Update is getting failed, getting error["+Errormsg+"], Please refer screenshot >> PopupDisplayedError"+fnsScreenShot_Date_format()+", Getting Exception >> "+t.getMessage() );
				throw new Exception("FAILED == Popup Update is getting failed, getting error["+Errormsg+"], Please refer screenshot >> PopupDisplayedError"+fnsScreenShot_Date_format() +", Getting Exception >> "+t.getMessage());}
			
		*/
		
		
		
		
		//Verify record updated.
		try{
			for(int start=0; start<60; start++){
				RowCountAfterUpdate=fnsTable_Total_RowCount_Fetch("EditClient_ClientContacts_TableHeaderXpath");
			//	System.out.println("RowCountAfterUpdate  " +RowCountAfterUpdate);
				if(RowCountAfterUpdate>RowCountBeforeUpdate){
					break;
				}else{Thread.sleep(1000);}
			}
			assert (RowCountAfterUpdate>RowCountBeforeUpdate):"FAILED == Record is not Added into Contacts TAB , Please refer screen shot ["+ "RecordNotAdded" + fnsScreenShot_Date_format() + " ].";
			fnsApps_Report_Logs("PASSED == Record is Successfully Added into ContactsTAB Table.");	
			
		}catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("RecordNotAdded");
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());}
		
		//Verify Popup value updated into table
		//fnsTableVerify_SingleColumnMatchingText_of_WebTableWithoutPagination("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", Client_Suitexls.getCellData("Search_CLNT", 2, 4), "4");
		//fnsTableVerify_SingleColumnMatchingText_of_WebTableWithoutPagination("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", Client_Suitexls.getCellData("Search_CLNT", 2, 5), "6");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Contacts TAB : "+Throwables.getStackTraceAsString(t));
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@Test(dependsOnMethods={"ContactsTAB_ClickOnClientLink_EditContactTAB_FillMandatoryData_in_ADDContactPopup_and_VerifyRecordsUpdated"}, priority = 4, description="  [Click on Edit contact button and fill in the changed phone number ( put the  phone number as mmddyyyy)information.]")
	public void ContactsTAB_ClickOnEditContactBttn_Enter_PhoneNumber_and_VerifyRecordsUpdated(String uniqueCode) throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("####################### Test Case ::::::4 ContactsTAB_ClickOnEditContactBttn_Enter_PhoneNumber_and_VerifyRecordsUpdated ");
	
	try{
		//fnsTableClicking_on_Table_Bttn_like_EditDelete("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName"), "EditClient_ClientContacts_EditContacts_Bttn_Xpath1","EditClient_ClientContacts_EditContacts_Bttn_Xpath2");  //07May2019 Referal Integrity Issue.
		fnsTableClicking_on_Table_Bttn_like_EditDelete("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", "AutoLName"+uniqueCode, "EditClient_ClientContacts_EditContacts_Bttn_Xpath1","EditClient_ClientContacts_EditContacts_Bttn_Xpath2");
		fnsGet_Element_Enabled("EditClient_Contacts_AddContact_Popup_Phone");
		fnsWait_and_Type("EditClient_Contacts_AddContact_Popup_Phone", fnsEditClient_Date_format());
		//System.out.println("--------------Satya -------------"+driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_Contacts_AddContact_Bttn"))).size());
		fnsWait_and_Click("EditClient_Contacts_AddContact_Popup_SaveandClose_Bttn");
	
		fnsLoading_Progressingwait(1);
		
		// Commented as not working for we are sorry
		/*TestSuiteBase_Aspirago.fnsLoading_Progressingwait(1);();
		Thread.sleep(5000);
	
		//Verify Popup Close without any error.		
		try{
			
			if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_Contacts_AddContact_Bttn"))).size()>0){fnsApps_Report_Logs("PASSED == Popup is successfully updated.");}
			else{
				Errormsg=fnsGet_Field_TEXT("EditClient_Contacts_AddContact_Popup_ErrorMsg_Div");
				assert !(Errormsg.contains("mandatory"));}
		}catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("PopupDisplayedError");
				fnsApps_Report_Logs("FAILED == Popup Update is getting failed, getting error["+Errormsg+"], Please refer screenshot >> PopupDisplayedError"+fnsScreenShot_Date_format()+", Getting Exception >> "+t.getMessage() );
				throw new Exception("FAILED == Popup Update is getting failed, getting error["+Errormsg+"], Please refer screenshot >> PopupDisplayedError"+fnsScreenShot_Date_format() +", Getting Exception >> "+t.getMessage());}
		*/
		
		//Verify Popup value updated into table
		fnsTableVerify_SingleColumnMatchingText_of_WebTableWithoutPagination("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Phone"), "7");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Contacts TAB : "+Throwables.getStackTraceAsString(t));
	}
}
	
	
	
	
	
	
	
	
	
	
	
	

	//@Test(dependsOnMethods={"ContactsTAB_ClickOnEditContactBttn_Enter_PhoneNumber_and_VerifyRecordsUpdated"}, priority = 5, description="  [Click on Delete X   button .Click on Yes for the message which come here: Are you sure you want to delete this contact?]")
	public void ContactsTAB_ClickOnDeleteBttn_and_VerifyRecords_are_Deleted_from_Table(String uniqueCode) throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("####################### Test Case ::::::5 ContactsTAB_ClickOnDeleteBttn_and_VerifyRecords_are_Deleted_from_Table ");
	
	try{
		Thread.sleep(2000);
		RowCountBeforeUpdate=fnsTable_Total_RowCount_Fetch("EditClient_ClientContacts_TableHeaderXpath");
		//System.out.println("RowCountBeforeUpdate  " +RowCountBeforeUpdate);
		
		
		//fnsTableClicking_on_Table_Bttn_like_EditDelete("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName"), "EditClient_ClientContacts_Delete_Bttn_Xpath1","EditClient_ClientContacts_Delete_Bttn_Xpath2");   "AutoLName"+uniqueCode
		fnsTableClicking_on_Table_Bttn_like_EditDelete("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", "AutoLName"+uniqueCode, "EditClient_ClientContacts_Delete_Bttn_Xpath1","EditClient_ClientContacts_Delete_Bttn_Xpath2");
		
		fnsLoading_Progressingwait(1);
		//fnpWaitForVisible("EditClient_ClientContacts_Delete_YES_Bttn");
		//fnsGet_Element_Enabled("EditClient_ClientContacts_Delete_YES_Bttn");
		//fnsWait_and_Click("EditClient_ClientContacts_Delete_YES_Bttn");
		fnsTableClicking_on_Delete_YES_Bttn("EditClient_ClientContacts_TableHeaderXpath", "EditClient_ClientContacts_TableCellHeaderXpath", "EditClient_ClientContacts_Delete_YES_Bttn_xpath1","EditClient_ClientContacts_Delete_YES_Bttn_xpath2");
			
			
		
		//Verify record updated.
		try{
			for(int start=0; start<60; start++){
				RowCountAfterUpdate=fnsTable_Total_RowCount_Fetch("EditClient_ClientContacts_TableHeaderXpath");
				//System.out.println("RowCountAfterUpdate  " +RowCountAfterUpdate);
				if(RowCountAfterUpdate<RowCountBeforeUpdate){
					break;
				}else{Thread.sleep(1000);}
			}
			
		assert (RowCountAfterUpdate<RowCountBeforeUpdate):"FAILED == Record is not Deleted from Table , Please refer screen shot ["+ "RecordNotDeleted" + fnsScreenShot_Date_format() + " ].";
		fnsApps_Report_Logs("PASSED == Record is Successfully Deleted from Table");	
			
		}catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("RecordNotDeleted");
			fnsApps_Report_Logs(", Getting Exception >> "+t.getMessage());
			throw new Exception(", Getting Exception >> "+t.getMessage());}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Contacts TAB : "+Throwables.getStackTraceAsString(t));
	}
}
	
	
	
	
	
	
	
	

	//@Test(dependsOnMethods={"ContactsTAB_ClickOnDeleteBttn_and_VerifyRecords_are_Deleted_from_Table"}, priority = 6, description="  [Click on the Code ->22492. Expand the View Client >Click on the  Corporate Parent #:22490]")
	public void FacilitiesTAB_ClickOnCode_ExpandTheViewClient_ClickOnCorporateParent() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("####################### Test Case ::::::6 FacilitiesTAB_ClickOnCode_ExpandTheViewClient_ClickOnCorporateParent ");
	
	try{
		fnsGet_Element_Enabled("ViewClient_Facilities_Tab");
		fnsWait_and_Click("ViewClient_Facilities_Tab");
		fnsLoading_Progressingwait(1);
		
		if(ORG_Unit.contains("PLUMB")){
			fnsTableFetch_RowNumber_For_Matching_TEXT("EditClient_FacilitiesTab_TableHeaderXpath","EditClient_FacilitiesTab_TableCellHeaderXpath",fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Code")); //i Value is coming=1
		
			fnsTable_ClickOn_LINK_ByMatchingText(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Code"));
		}
		
		if(ORG_Unit.contains("ISR")){
			fnsGet_Element_Enabled("EditClient_FacilitiesTab_First_Facility_Code_Link");
			fnsWait_and_Click("EditClient_FacilitiesTab_First_Facility_Code_Link");
		}
		
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("EditClient_FacilitiesTab_GoToInsight_Bttn");
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("ViewClient_ExpandClient_PlusSign_Button");
		fnsWait_and_Click("ViewClient_ExpandClient_PlusSign_Button");
		
		
		
		/*for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
			if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_FacilitiesTab_FRSReportBttn"))).size()>0){
				Thread.sleep(1000);
				fnsGet_Element_Enabled("ViewClient_ExpandClient_PlusSign_Button");
				fnsWait_and_Click("ViewClient_ExpandClient_PlusSign_Button");
				break;
			}else{
				Thread.sleep(1000);
			}
		}*/
		
		
		fnsGet_Element_Enabled("ViewClient_ExpandClient_Corporate_Parent_TextField");
		fnsWait_and_Click("ViewClient_ExpandClient_Corporate_Parent_TextField");
		fnsLoading_Progressingwait(1);
		
		for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
			if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_FacilitiesTab_FRSReportBttn"))).size()>0){
				Thread.sleep(1000);
			}else{
				Thread.sleep(1500);
				break;
			}
		}
		
		fnsGet_Element_Enabled("ViewClient_ExpandClient_Client_TextField");
		if(ORG_Unit.contains("PLUMB")){
			fnsText_Fetch_and_Assert("ViewClient_ExpandClient_Client_TextField", Client_22490);
		}
		
		if(ORG_Unit.contains("ISR")){
			fnsText_Fetch_and_Assert("ViewClient_ExpandClient_Client_TextField", ISR_Client_ID);
		}
				
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Facilities TAB : "+Throwables.getStackTraceAsString(t));
	}
}
	
	
	
	

	//@Test(dependsOnMethods={"FacilitiesTAB_ClickOnCode_ExpandTheViewClient_ClickOnCorporateParent"}, priority = 7, description="  [Click on the Correspondence tab>Click Edit button > Click on Add button. Select the template as: Client Notification 'General Template'. Fill in the information for From,To,Reply To and click Send Email]")
	public void CorrespondenceTAB_EditCorrespondence_FillMandatoryData_in_ADDCorrespondencePopup_and_VerifyRecordsUpdated(String emailTempOrgUnitWise) throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("############ Test Case ::::::7 CorrespondenceTAB_EditCorrespondence_FillMandatoryData_in_ADDCorrespondencePopup_and_VerifyRecordsUpdated ");
	
	try{
		//Thread.sleep(10000); //temp
		fnsGet_Element_Enabled("ViewClient_Correspondence_Tab");
		fnsWait_and_Click("ViewClient_Correspondence_Tab");
		fnsLoading_Progressingwait(1);
		
		
		//Clicking on Edit button 
		for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
					if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_CorrespondenceTab_TableHeaderXpath"))).size()>0){
						fnsWait_and_Click("ViewClient_Edit_Bttn");
						break;}
					else{Thread.sleep(1000);}
		}
			
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("EditClient_CorrespondenceTab_Add_Bttn");
		fnsWait_and_Click("EditClient_CorrespondenceTab_Add_Bttn");
		
		fnsLoading_Progressingwait(1);
		RowCountBeforeUpdate=fnsTable_Total_RowCount_Fetch("EditClient_CorrespondenceTab_TableHeaderXpath");
		if( (fnsGet_Field_TEXT("EditClient_CorrespondenceTab_TableCellHeaderXpath").toLowerCase()).contains("no records found") ){
			RowCountBeforeUpdate = 0;
		}
		//System.out.println("RowCountBeforeUpdate  " +RowCountBeforeUpdate);
		
		fnsGet_Element_Enabled("EditClient_CorrespondenceTab_AddPopup_EmailTemplate_DD_Click");
		fnsDD_value_Select_By_MatchingText("EditClient_CorrespondenceTab_AddPopup_EmailTemplate_DD_Click", "EditClient_CorrespondenceTab_AddPopup_EmailTemplate_DD_Value", "li", emailTempOrgUnitWise );
		Thread.sleep(1000);
		fnsLoading_Progressingwait(1);
		fnsWait_and_Click("EditClient_CorrespondenceTab_AddPopup_Continue_Bttn");
		
		fnsGet_Element_Enabled("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_From");
		fnsWait_and_Clear("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_From");
		fnsWait_and_Type("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_From", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email_From"));
		
		fnsWait_and_Clear("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_To");
		fnsWait_and_Type("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_To", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email_To"));
		
		fnsWait_and_Clear("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_ReplyTo");
		fnsWait_and_Type("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_ReplyTo", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email_ReplyTo"));
		
		fnsWait_and_Click("EditClient_CorrespondenceTab_NewEmailCorrespondencePopUp_SendEmail");
		fnsLoading_Progressingwait(5);
			
		try{
			for(int start=0; start<60; start++){
				RowCountAfterUpdate=fnsTable_Total_RowCount_Fetch("EditClient_CorrespondenceTab_TableHeaderXpath");
				//System.out.println("RowCountAfterUpdate  " +RowCountAfterUpdate);
				if(RowCountAfterUpdate>RowCountBeforeUpdate){
					break;
				}else{Thread.sleep(1000);}
			}
			fnsLoading_Progressingwait(1);
			assert (RowCountAfterUpdate>RowCountBeforeUpdate):"FAILED == Record is not Added into Correspondence Table , Please refer screen shot ["+ "RecordNotAdded" + fnsScreenShot_Date_format() + " ]";
			fnsApps_Report_Logs("PASSED == Record is Successfully Added into Correspondence Table.");	
			
		}catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("RecordNotAdded");
			fnsApps_Report_Logs(", Getting Exception >> "+t.getMessage());
			throw new Exception(", Getting Exception >> "+t.getMessage());}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Correspondence TAB : "+Throwables.getStackTraceAsString(t));
	}
}	


	
	
	
	
	
	

	
	
	
	//@Test(dependsOnMethods={"CorrespondenceTAB_EditCorrespondence_FillMandatoryData_in_ADDCorrespondencePopup_and_VerifyRecordsUpdated"}, priority = 8, description="  [Click on Edit button.Click on Add New button.Select the Relationship type and code from the dropdown.Click on save button]")
	public void LinksTAB_Edit_ClickOnAddNewBttn_FillMandatoryData_and_SaveIt() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("############ Test Case ::::::8 LinksTAB_Edit_ClickOnAddNewBttn_FillMandatoryData_and_SaveIt ");
	
	try{
		fnsGet_Element_Enabled("ViewClient_Links_Tab");
		Thread.sleep(3000);
		fnsWait_and_Click("ViewClient_Links_Tab");
		fnsLoading_Progressingwait(1);
		
		//Need to Comment these lines of code
		//Thread.sleep(5000);
		//fnsWait_and_Click("ViewClient_Edit_Bttn");
		
		
		//Clicking on Link TAB on view contact page.
		for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
					if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_LinksTab_TableHeaderXpath"))).size()>0){break;}
					else{
						fnsWait_and_Click("ViewClient_Links_Tab");
						Thread.sleep(2000);
						}
		}
		
		
		/*
		//Clicking on Edit button 
		//for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
				if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_LinksTab_TableHeaderXpath"))).size()>0){
					fnsWait_and_Click("ViewClient_Edit_Bttn");
					break;}
				else{Thread.sleep(1000);}
		}
		*/
			
			
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("EditClient_LinksTab_Add_Bttn");
		fnsWait_and_Click("EditClient_LinksTab_Add_Bttn");
		
		fnsLoading_Progressingwait(2);
		fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditClient_LinksTab_RelationshipType_DD_Click");
		fnsWait_and_Click("EditClient_LinksTab_RelationshipType_DD_Click");
		Thread.sleep(2000);
		
		//fnsTable_ClickOn_LINK_ByMatchingText(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "RelationshipType_DD"));
		fnsTable_Select_ByMatchingText(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "RelationshipType_DD"));
		//fnsDD_value_Select_By_MatchingText("EditClient_LinksTab_RelationshipType_DD_Click", "EditClient_LinksTab_RelationshipType_DD_Value", "option", Client_Suitexls.getCellData("Search_CLNT", 2, 18) );
		
		fnsWait_and_Click("EditClient_LinksTab_Code_LookUp_Bttn");
		fnsGet_Element_Enabled("EditClient_LinksTab_Code_LookUp_TextValue_Field");
		fnsWait_and_Type("EditClient_LinksTab_Code_LookUp_TextValue_Field", Client_22490);
		fnsWait_and_Type("SearchClient_CorpFacility_LookUp_ClientNameSearch_Field", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorpFacility_LookUp").split("\\,")[1].trim());
		fnsLookup_RadioBttn_Select();
		
		Thread.sleep(5000);
		fnsGet_Element_Enabled("EditClient_LinksTab_Save_Bttn");
		fnsWait_and_Click("EditClient_LinksTab_Save_Bttn");
		
		Thread.sleep(3000);
		fnsLoading_Progressingwait(1);
		fnsText_Fetch_and_Assert("Updated_Message1", "success");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Links TAB : "+Throwables.getStackTraceAsString(t));
	}
	
	
}
	
	
	
	
	
	//@Test(dependsOnMethods={"LinksTAB_Edit_ClickOnAddNewBttn_FillMandatoryData_and_SaveIt"}, priority = 9, description="  [Click on Edit button to edit the link.Do the changes and click on Save button.  -----------        2. Click on delete button to delete the link.]")
	public void LinksTAB_Edit_ClickOnEditBttn_and_SaveIt_Then_Delete_the_Link() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("############ Test Case ::::::9 LinksTAB_Edit_ClickOnEditBttn_and_SaveIt_Then_Delete_the_Link ");
	
	try{
		Thread.sleep(3000);
		fnsGet_Element_Enabled("EditClient_LinksTab_Edit_Bttn");
		fnsWait_and_Click("EditClient_LinksTab_Edit_Bttn");
			
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("EditClient_LinksTab_RelationshipType_DD_Click");
		fnsWait_and_Click("EditClient_LinksTab_RelationshipType_DD_Click");
		fnsTable_ClickOn_LINK_ByMatchingText(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Edit_RelationshipType_DD"));
		
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("EditClient_LinksTab_Save_Bttn");
		fnsWait_and_Click("EditClient_LinksTab_Save_Bttn");
		Thread.sleep(3000);
		fnsLoading_Progressingwait(1);
		fnsText_Fetch_and_Assert("Updated_Message1", "success");
			
		fnsGet_Element_Enabled("EditClient_LinksTab_Delete_Bttn");
		fnsWait_and_Click("EditClient_LinksTab_Delete_Bttn");
		fnsLoading_Progressingwait(1);
	/*	fnsGet_Element_Enabled("EditClient_LinksTab_Delete_YES_Bttn_xpath");
		fnsWait_and_Click("EditClient_LinksTab_Delete_YES_Bttn_xpath");
		fnsLoading_Progressingwait(1);*/
		
		
		try{
			for(int i=1; i<=40; i++){
				if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_LinksTab_Delete_YES_Bttn_xpath"))).size()>0){
					if(driver.findElement(By.xpath(OR_CLNT.getProperty("EditClient_LinksTab_Delete_YES_Bttn_xpath"))).isDisplayed()){
						driver.findElement(By.xpath(OR_CLNT.getProperty("EditClient_LinksTab_Delete_YES_Bttn_xpath"))).click();
					}else{
						fnsLoading_Progressingwait(1);
						break;
					}
				}else{
					Thread.sleep(3000);
				}
				if( !(driver.findElement(By.xpath(OR_CLNT.getProperty("EditClient_LinksTab_Delete_YES_Bttn_xpath"))).isDisplayed()) ){
					fnsLoading_Progressingwait(1);
					break;
				}
			}
		}catch(Throwable t){
			Thread.sleep(3000);
		}
		
	
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("Links TAB : "+Throwables.getStackTraceAsString(t));
	}
}	
	
	
	
	
	
	
	
	
	
	

	//@Test(dependsOnMethods={"LinksTAB_Edit_ClickOnEditBttn_and_SaveIt_Then_Delete_the_Link"}, priority = 10, description="  [Click on Edit button.Click on Add New button.Select the Relationship type and code from the dropdown.Click on save button]")
	public void BillingInfoTAB_Edit_ClickOnAddBillingInfoBttn_FillData_in_PopUp_and_SaveIt() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");	
	fnsApps_Report_Logs("############ Test Case ::::::10 BillingInfoTAB_Edit_ClickOnAddBillingInfoBttn_FillData_in_PopUp_and_SaveIt ");
	
	try{
		Thread.sleep(3000);
		fsnClickOn_Screen_TAB_Pagination_NEXT_Link_Till_DisAppear();
		fnsWait_and_Click("ViewClient_BillingInfo_Tab");
		fnsLoading_Progressingwait(1);
		
		//Clicking on Billing info TAB button on view contact page.
		for(int wait=0; wait<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++){
				if(driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_BillingInfoTab_Add_Bttn"))).size()>0){break;}
				else{
					fnsWait_and_Click("ViewClient_BillingInfo_Tab");
					Thread.sleep(2000);}
		}
	
		//Not working if one record present
		/*/RowCountBeforeUpdate=fnsTable_Total_RowCount_Fetch("EditClient_BillingInfoTab_TableHeaderXpath");
		System.out.println("satya RowCountBeforeUpdate = "+RowCountBeforeUpdate);
		if( (fnsGet_Field_TEXT("EditClient_BillingInfoTab_TableCellHeaderXpath").toLowerCase()).contains("no records found") ){
			RowCountBeforeUpdate = 3;
		}/*/	
		
		
		RowCountBeforeUpdate=fnsTable_Total_RowCount_Fetch("EditClient_BillingInfoTab_TableHeaderXpath");
		if( RowCountBeforeUpdate<=2 ){
			RowCountBeforeUpdate = 3;
		}
		
		
		
		
		//Need to Comment -- TEMP
		//Thread.sleep(3000);
		//fnsWait_and_Click("ViewClient_Edit_Bttn");
			
		//System.out.println("EditClient_BillingInfoTab_Edit_Bttn="+ driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_BillingInfoTab_Edit_Bttn"))).size());
		fnsGet_Element_Enabled("EditClient_BillingInfoTab_Add_Bttn");
		fnsWait_and_Click("EditClient_BillingInfoTab_Add_Bttn");	
		
		Thread.sleep(3000);	
		//System.out.println("EditClient_BillingInfoTab_Edit_Bttn="+ driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_BillingInfoTab_Edit_Bttn"))).size());
		//fnsGet_Element_Enabled("EditClient_BillingInfoTab_Edit_Bttn");
		//fnsWait_and_Click("EditClient_BillingInfoTab_Edit_Bttn");
		
		
		
		fnsGet_Element_Enabled("EditClient_BillingInfoTab_BillingCode_Lookup_Bttn");
		fnsWait_and_Click("EditClient_BillingInfoTab_BillingCode_Lookup_Bttn");
		
		fnsGet_Element_Enabled("EditClient_BillingInfoTab_BillingCode_Lookup_TextValue_Field");
		fnsWait_and_Type("EditClient_BillingInfoTab_BillingCode_Lookup_TextValue_Field", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BillingCode_Lookup"));
		fnsLookup_RadioBttn_Select();
		
		Thread.sleep(3000);
		fnsGet_Element_Enabled("EditClient_BillingInfoTab_Save_Bttn");
		fnsWait_and_Click("EditClient_BillingInfoTab_Save_Bttn");
		
		fnsText_Fetch_and_Assert("Updated_Message1", "success");
		
		Thread.sleep(4000);
		
		/*	
		
		RowCountBeforeUpdate=fnsTable_Total_RowCount_Fetch("EditClient_BillingInfoTab_TableHeaderXpath");
		System.out.println("satya RowCountBeforeUpdate = "+RowCountBeforeUpdate);
		if( (fnsGet_Field_TEXT("EditClient_BillingInfoTab_TableCellHeaderXpath").toLowerCase()).contains("no records found") ){
			RowCountBeforeUpdate = 3;
		}	*/
		
		//System.out.println("RowCountBeforeUpdate  " +RowCountBeforeUpdate);
		
		
		
		fnsWait_and_Click("EditClient_BillingInfoTab_Delete_Bttn");
		fnsGet_Element_Enabled("EditClient_BillingInfoTab_Delete_YES_Bttn_xpath");
		fnsWait_and_Click("EditClient_BillingInfoTab_Delete_YES_Bttn_xpath");
		//fnsTableClicking_on_Delete_YES_Bttn("EditClient_BillingInfoTab_TableHeaderXpath", "EditClient_BillingInfoTab_TableCellHeaderXpath", "EditClient_BillingInfoTab_Delete_YES_Bttn_xpath1","EditClient_BillingInfoTab_Delete_YES_Bttn_xpath2");
		Thread.sleep(4000);
		fnsLoading_Progressingwait(1);
		boolean Pagination_Flag = driver.findElements(By.xpath(OR_CLNT.getProperty("EditClient_BillingInfoTab_Pagination"))).size()>0;
		
		//Verify record updated.
		if(Pagination_Flag){
			Thread.sleep(4000);
			fnsLoading_Progressingwait(1);
			fnsApps_Report_Logs("PASSED == Record is Successfully Deleted from Table <Pagination Coming>");	
		}else{
			try{
				for(int start=0; start<60; start++){
					RowCountAfterUpdate=fnsTable_Total_RowCount_Fetch("EditClient_BillingInfoTab_TableHeaderXpath");
					System.out.println(" RowCountBeforeUpdate = "+RowCountBeforeUpdate+" RowCountAfterUpdate = "+RowCountAfterUpdate);
					//System.out.println("RowCountAfterUpdate  " +RowCountAfterUpdate);
					if(RowCountAfterUpdate<RowCountBeforeUpdate){
						break;
					}else{Thread.sleep(1000);}
				}
				
			assert (RowCountAfterUpdate<RowCountBeforeUpdate):"FAILED == Record is not Deleted from Table , Please refer screen shot ["+ "RecordNotDeleted" + fnsScreenShot_Date_format() + " ]";
			fnsApps_Report_Logs("PASSED == Record is Successfully Deleted from Table");	
			
			}catch(Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("RecordNotDeleted");
				fnsApps_Report_Logs(", Getting Exception >> "+t.getMessage());
				throw new Exception(", Getting Exception >> "+t.getMessage());}
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception("BillingInfo TAB : "+Throwables.getStackTraceAsString(t));
	}
}
	
	

	public static void fncVerify_ClickonAjax_then_SearchCriteria_OrgUnitAs_ListStatus_ClientType(String ORg_Unit_Value) throws Throwable{
		try{
			fnSearchClient_Ajax_Link_Click();			
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled("Footer");
			Thread.sleep(1000);
			fnsGet_Element_Enabled("SearchClient_Search_Bttn");
			Thread.sleep(500);
			
			fnsMove_To_Element_and_Click("SearchClient_ClientType");
			fnsWait_and_Click("SearchClient_ClientType");
			Thread.sleep(1000);
			
			WithOut_OR_fnsDD_value_Select_From_Filter_CheckBox(OR_CLNT.getProperty("SearchClient_OrgUnit_DD_Click"), ORg_Unit_Value);
			//fnsLoading_Progressingwait(1);
			
			WithOut_OR_fnsDD_value_Select_From_Filter_CheckBox(OR_CLNT.getProperty("SearchClient_ListStatus_DD_Click"), "APPLY");
			//fnsLoading_Progressingwait(1);
			
			fnsWait_and_Click("SearchClient_Search_Bttn");
			fnsLoading_Progressingwait(1);
			
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception("Search Criteria : "+Throwables.getStackTraceAsString(t));
		}
		
	}
	
	
	
	
	
	
	
	
	
//##################################################################################################################################################################################################
//###########################################################       Configuration methods     #####################################################################################################	
	
	
@AfterClass
public void DeleteQuery_for_Correspondence_TAB() throws Throwable{
	fnsDeleteQuery_for_CorrespondenceTAB_in_EditClient();
}
	
	
	
@AfterMethod
public void reportDataSetResult() {
	if(count>-1){
		if (skip)
			TestUtil.reportDataSetResult(Client_Suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
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
	fns_ReportTestResult_atClassLevel(Client_Suitexls,  (this.getClass().getSimpleName()) );
}	



@AfterMethod
public void QuitBrowser(){
	if( IsBrowserPresentAlready ){
		MoveMouseCursorToTaskBar();
		driver.quit();
		IsBrowserPresentAlready = false;
	}
}

	

@DataProvider
public Object[][] getTestData() {
	return getExcelData_for_DataProvider(Client_Suitexls, this.getClass().getSimpleName());
}
	
	
}