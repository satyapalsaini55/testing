package nsf.ecap.New_NSFOnline_Suite;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_06_Suppliers_SearchSupplier extends TestSuiteBase_New_NSFOnline {
	

	public int count = -1;
	public boolean BrowserOpen = false;
		
	
	 


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	fnsCheckClassLevelTestSkip(className);
	
}

	

	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_Supplier_and_TheirDocuments_are_Displayed_as_Expected__(String Serial_No, String LoginUserName, String Search_SuppliersIDs,String Document_Verification, String Multi_Access) throws Exception{
		BrowserOpen = false;
		fail = false;
		count++;
		String Login_User_Name = LoginUserName.split("=")[1].trim();
		String CaseSerialNo = Serial_No.split("=")[1];
		Login_Password = "welcome1010";
		String Search_SupplierID = null;
		TC_Step=1;		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" for Login User ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" for Login User ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
			}else{
				BrowserOpen = true;
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" for Login User ["+Login_User_Name+"].");
				
				fnsBrowserLaunchAndLogin(Login_User_Name, Login_Password);
				fnsSwitchAcoount_MultiAccess(null, Multi_Access);
				
				if(driver.getTitle().toLowerCase().trim().contains("nsfonline")){
					fnsTake_Screen_Shot("NSFOnline_2.0_Open");
					throw new Exception("'NsfOnline 1.0' is getting open instead of 'NsOnline 2.0'"+" , Please refer the screen shot >> NSFOnline_2.0_Open"+fnsScreenShot_Date_format());
				}
				
				fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Suppliers_Ajax", "Suppliers_SubMenu_Suppliers_Ajax");	
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Suppliers' Menu Ajax", "Suppliers");
				/*
				fnsLoading_Progressing_wait(5);
				for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
						fnsApps_Report_Logs("PASSED == 'Suppliers' screen opened.");
						fnsLoading_Progressing_wait(2);
						break;
					}else{
						Thread.sleep(1000);
					}
					if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
						fnsTake_Screen_Shot("SuppliersScreen_Open_Fail");
						throw new Exception("FAILED == 'Suppliers' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> SuppliersScreen_Open_Fail"+fnsScreenShot_Date_format());
					}
				}*/
				
				fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
				fnsWait_and_Click("View_AdvanceSearch_Bttn");
				fnsLoading_Progressing_wait(5);
								
				fnsGet_Element_Enabled("SuppliersAdvanceSearch_SupplierCode");
				
	
				
				String NoOfSets[] = Search_SuppliersIDs.split(":");
				fnsApps_Report_Logs("Sets data are ["+Search_SuppliersIDs+"]. and No. of Suppliers Sets are == " + NoOfSets.length);
		
				for (int j = 0; j < NoOfSets.length; j++) {
			
					String SupplierSet = NoOfSets[j];
					fnsApps_Report_Logs("***************** Current searching Supplier Set  is [" + SupplierSet+"]  ******************");
			
					String CurrentSet[] = SupplierSet.split(",");
	
					Search_SupplierID = fnsRemoveFormatting(CurrentSet[0]);
					String SearchId_Condition_GoodBad = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
						
					fnsWait_and_Clear("SuppliersAdvanceSearch_SupplierCode");
					fnsWait_and_Type("SuppliersAdvanceSearch_SupplierCode", Search_SupplierID);
					
					fnsWait_and_Click("SuppliersAdvanceSearch_Search_Bttn");
					fnsLoading_Progressing_wait(5);
					fnsGet_Element_Enabled("SuppliersAdvanceSearch_Result_Table");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("SuppliersAdvanceSearch_Result_Table"));
					String TextFetch=fnsGet_Field_TEXT("SuppliersAdvanceSearch_Result_Table").toLowerCase().trim();
									
					if (SearchId_Condition_GoodBad.equalsIgnoreCase("good")) {
						try{
							assert TextFetch.contains(Search_SupplierID.toLowerCase().trim()):"FAILED == Supplier ID <"+Search_SupplierID+"> for User ("+Login_User_Name+") is not coming 'GOOD'<i.e. Not appear into the Advance Search results Table>, Please refer Screen shot >>"+Search_SupplierID+"_GoodFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED ==  Successfully Verify that Supplier ID <"+Search_SupplierID+"> for User ("+Login_User_Name+") is coming 'GOOD'.");
						}catch(Throwable t){
							fnsTake_Screen_Shot(Search_SupplierID+"_GoodFail");
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
					}else if (SearchId_Condition_GoodBad.equalsIgnoreCase("bad")) {
						try{
							assert TextFetch.contains("no records found"):"FAILED == Supplier ID <"+Search_SupplierID+"> for User ("+Login_User_Name+") is not coming 'BAD'<i.e. Appear into the Advance Search results Table>, Please refer Screen shot >>"+Search_SupplierID+"_BadFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED ==  Successfully Verify that Supplier ID <"+Search_SupplierID+"> for User ("+Login_User_Name+") is coming 'BAD'.");
						}catch(Throwable t){
							fnsTake_Screen_Shot(Search_SupplierID+"_BadFail");
							throw new Exception(Throwables.getStackTraceAsString(t));	
						}
					}else{
						throw new Exception("FAILED == '"+SearchId_Condition_GoodBad.toUpperCase()+"' is mentioned for Supplier ID <"+Search_SupplierID+"> instead of mentioning Good/Bad into the Excel data sheet, Please correct it." );
					}
					
					
					
					
					

					if(Document_Verification.trim().length()>3){
						
						String Document_No = Document_Verification.split(":")[j].trim();
						String Search_Supplier_ID_Link = "//a[text()='"+Search_SupplierID+"']";
						fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Search_SupplierID, Search_Supplier_ID_Link);
						fnsLoading_Progressing_wait(3);
						for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
							if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Supplier_Dcouments_TAB"))).size()>0){
								fnsApps_Report_Logs("PASSED == 'Suppliers - Supplier info' screen opened.");
							//	fnsLoading_Progressing_wait(2);
								break;
							}else{
								Thread.sleep(1000);
							}
							if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
								fnsTake_Screen_Shot("SuppliersScreen_Open_Fail");
								throw new Exception("FAILED == 'Suppliers - Supplier info' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> SuppliersScreen_Open_Fail"+fnsScreenShot_Date_format());
							}
						}
						
						
						fnsGet_Element_Enabled("Supplier_Dcouments_TAB");
						fnsWait_and_Click("Supplier_Dcouments_TAB");
						fnsLoading_Progressing_wait(3);

						fnsGet_Element_Enabled("Supplier_DocumentTAB_Result_Table");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("Supplier_DocumentTAB_Result_Table"));
						String Document_TAB_Table_Text=fnsGet_Field_TEXT("Supplier_DocumentTAB_Result_Table").toLowerCase().trim();
						
						if(Document_No.equalsIgnoreCase("NA")){
							try{
								assert (Document_TAB_Table_Text.contains("no records found")):"FAILED == Records are coming into the Document TAB instead of 'NO RECORD' under supplier ("+Search_SupplierID+"), Please refer Screenshot>>"+"NoRecordsToDisplay_FAIL_"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully Verified that 'No Records' are coming into the Document TAB under supplier ("+Search_SupplierID+").");
							}catch(Throwable t){
								fnsTake_Screen_Shot("NoRecordsToDisplay_FAIL_");
								throw new Exception(t.getMessage());	}
						}else{
							try{
								assert (Document_TAB_Table_Text.contains(Document_No.toLowerCase())):"FAILED == Document<"+Document_No+"> is not coming into the Document TAB  under supplier ("+Search_SupplierID+"), Please refer Screenshot>>"+Document_No+"_DisplayFAIL_"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully Verified that Document<"+Document_No+"> is coming into the Document TAB under supplier ("+Search_SupplierID+").");
							}catch(Throwable t){
								fnsTake_Screen_Shot(Document_No+"_DisplayFAIL_");
								throw new Exception(t.getMessage());	}
						}
						
						
						if(j<1){
							fnsGet_Element_Enabled("Supplier_SupplierInfo_Back_Bttn");
							fnsWait_and_Click("Supplier_SupplierInfo_Back_Bttn");
							fnsLoading_Progressing_wait(5);
							for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
								if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SuppliersAdvanceSearch_Search_Bttn"))).size()>0){
									fnsApps_Report_Logs("PASSED == 'Suppliers - Advance Search' screen opened.");
									fnsLoading_Progressing_wait(2);
									break;
								}else{
									Thread.sleep(1000);
								}
								if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
									fnsTake_Screen_Shot("SuppliersAdvanceSearchScreen_Open_Fail");
									throw new Exception("FAILED == 'Suppliers - Advance Search' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> SuppliersAdvanceSearchScreen_Open_Fail"+fnsScreenShot_Date_format());
								}
							}
						}
					}
				}				
			}
		//	isTestCasePass = true;
		}catch(SkipException sk){
			throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs("Case:"+CaseSerialNo.trim()+Throwables.getStackTraceAsString(t));
			throw new Exception("Case:"+CaseSerialNo.trim()+Throwables.getStackTraceAsString(t));
			
		}
}

	

	
	

	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
}

	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	

	@AfterMethod
	public void QuitBrowser(){
		if(BrowserOpen){
			driver.quit();
		}
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());
	}


}
