package nsf.ecap.IssueMgt_Suite;

import java.util.ArrayList;
import java.util.List;

import nsf.ecap.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;

public class DesiredOptions_Duplicates_Check extends TestSuiteBase_IM{
	
	public static String TitleShortDescriptionText="Esc_PRQ_WithoutAlerts[BS-19], D/T=";	
	public static boolean IsBrowserPresentAlready = false;
	public static String Escalation_IssueType_dd_value;
	public static String Issue_IssueType_dd_value;
	public static String ClientChargesConfiguration_IssueType_dd_value;
	
	
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
			
			if (!TestUtil.isTestCaseRunnable(IssueMgt_Suitexls, className)) {
				fnApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			fnApps_Report_Logs("=========================================================================================================================================");
		}
		catch(SkipException sk){
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}
		catch (Throwable t) {
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = Throwables.getStackTraceAsString(t);
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg );
		}
}

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("##################################    [BS-20] Check for desired options Duplicates in Issue Type DD    ############################################");
	
	Escalation_IssueType_dd_value = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Escalation_IssueType_dd");
	Issue_IssueType_dd_value = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Issue_IssueType_dd");
	ClientChargesConfiguration_IssueType_dd_value = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ClientChargesConfiguration_IssueType_dd");
	
	
	
	
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}
	
	
	


	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="SearchEscalation_ (Check that these values are present for Issue Type dropdown: AFP,Distribution,Enforcement Liaison,Foreign Body,Incident,Legal/Technical Advice,Non-Conformance,Product Quality)")
	public void Escalation_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");		
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown ");
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Search_Escalations");
		fncVerify_for_Duplicate_and_CrossCheck_of_DropDownValues("SearchEscalation_IssueType_dd_click", "SearchEscalation_IssueType_dd_value", Escalation_IssueType_dd_value);
		
	}
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown"}, priority = 2, description="SearchIssue__ (Check that these values are present for Issue Type dropdown: AFP,Distribution,Enforcement Liaison,Foreign Body,Incident,Legal/Technical Advice,Non-Conformance,Product Quality)")
	public void Issue_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");		
		fnApps_Report_Logs("################### Test Case ::::::2 Issue_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown ");
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Search_Issues");
		fncVerify_for_Duplicate_and_CrossCheck_of_DropDownValues("SearchIssue_IssueType_dd_click", "SearchIssue_IssueType_dd_value", Issue_IssueType_dd_value );
				
	}	
	
	
	
	
	@Test(dependsOnMethods={"Issue_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown"}, priority = 3, description="SearchClientChargesConfiguration__ (Check that these values are present for Issue Type dropdown: AFP,Distribution,Enforcement Liaison,Foreign Body,Incident,Legal/Technical Advice,Non-Conformance,Product Quality)")
	public void ClientChargesConfiguration_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");		
		fnApps_Report_Logs("################### Test Case ::::::3 ClientChargesConfiguration_CheckFor_DesiredOptions_Duplicates_in_IssueType_DropDown ");
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Client_Charges_Configuration");	
		fncVerify_for_Duplicate_and_CrossCheck_of_DropDownValues("SearchClientChargesConfiguration_IssueType_dd_click", "SearchClientChargesConfiguration_IssueType_dd_value", ClientChargesConfiguration_IssueType_dd_value);
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//##########################################################################################################################################################################	
//################################################################## Class Functions ########################################################################################	

	public static void fncVerify_for_Duplicate_and_CrossCheck_of_DropDownValues(String ddClickXpathKey, String ddListXpathKey, String Value) throws Exception {
		ArrayList<String> Apps_DD_List = new ArrayList<String>();
		ArrayList<String> DD_Values_from_Excel = new ArrayList<String>();
		ArrayList<String> Orignal_DD_Values_from_Excel = new ArrayList<String>();
		ArrayList<String> Orignal_Apps_DD_List = new ArrayList<String>();
		
		try{
			
			for(int i=0; i<Value.split(",").length; i++){
				DD_Values_from_Excel.add(Value.split(",")[i].toLowerCase().trim());
				Orignal_DD_Values_from_Excel.add(Value.split(",")[i].trim());
			}
			
			fnWait_and_Click(ddClickXpathKey);
			fnGet_Element_Enabled(ddListXpathKey);
			List<WebElement> objectlists=fnGet_OR_IM_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
					
		
			for(WebElement dd_value:objectlists){
				Apps_DD_List.add(dd_value.getText().toLowerCase().trim());	
				Orignal_Apps_DD_List.add(dd_value.getText().trim());	
			}
			
			
			for(int i=0; i<Apps_DD_List.size(); i++){
				Integer DuplicateCount = 0;
				for (int j=0; j<Apps_DD_List.size(); j++){
					if(Apps_DD_List.get(j).equals(Apps_DD_List.get(i))){
						DuplicateCount++;
					}
				}
				if(DuplicateCount>1){
					throw new Exception ("FAILED == Duplicate : Value<"+Orignal_Apps_DD_List.get(i)+"> is coming "+DuplicateCount+" times into the Issue Type Drop down, please refer screen shot >> Duplicate_Verification_Fail"+fnScreenShot_Date_format());
				}			
			}
			fnApps_Report_Logs("PASSED == ......................'Issue Type' Drop Down doesn't contains duplicate values.");
			
			

			for(int i=0; i<DD_Values_from_Excel.size(); i++){
				Integer DuplicateCount = 0;
				if(Apps_DD_List.contains(DD_Values_from_Excel.get(i))){
					DuplicateCount++;
				}
				
				if(DuplicateCount==0){
					throw new Exception ("FAILED == Excel Value<"+Orignal_DD_Values_from_Excel.get(i)+"> is not exists into the 'Issue Type' Drop down"+Orignal_Apps_DD_List+", please refer screen shot >> Duplicate_Verification_Fail"+fnScreenShot_Date_format());
				}			
			}
			fnApps_Report_Logs("PASSED == ......................All the values of Excel are exists into the 'Issue Type' Drop Down.");
			
			
			
			for(int i=0; i<Apps_DD_List.size(); i++){
				Integer DuplicateCount = 0;
				if(DD_Values_from_Excel.contains(Apps_DD_List.get(i))){
					DuplicateCount++;
				}
				
				if(DuplicateCount==0){
					throw new Exception ("FAILED == 'Issue Type' Drop down Value<"+Orignal_Apps_DD_List.get(i)+"> is not exists into the Excel Value"+Orignal_DD_Values_from_Excel+", please refer screen shot >> Duplicate_Verification_Fail"+fnScreenShot_Date_format());
				}			
			}
			fnApps_Report_Logs("PASSED == ......................All the values of 'Issue Type' Drop down are exists into the Excel.");
			
			
			
		/*	
			assert Apps_DD_List.containsAll(DD_Values_from_Excel) :"FAILED ==  Excel values "+DD_Values_from_Excel.size()+" "+DD_Values_from_Excel+" are more as compare 'Issue Type' Drop down values "+Apps_DD_List.size()+" "+Apps_DD_List+" , please refer screen shot >> Duplicate_Verification_Fail"+fnScreenShot_Date_format();
			fnApps_Report_Logs("PASSED == Excel values "+DD_Values_from_Excel.size()+" "+DD_Values_from_Excel+" are matched with 'Issue Type' Drop down values "+Apps_DD_List.size()+" "+Apps_DD_List+".");
			
			assert DD_Values_from_Excel.containsAll(Apps_DD_List) :"FAILED == 'Issue Type' Drop down values  "+Apps_DD_List.size()+" "+Apps_DD_List+" are more as compare to Excel values "+DD_Values_from_Excel.size()+" "+DD_Values_from_Excel+", please refer screen shot >> Duplicate_Verification_Fail"+fnScreenShot_Date_format();
			fnApps_Report_Logs("PASSED == 'Issue Type' Drop down values  "+Apps_DD_List.size()+" "+Apps_DD_List+" are matched with Excel values "+DD_Values_from_Excel.size()+" "+DD_Values_from_Excel+".");
			
			*/
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("Duplicate_Verification_Fail");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}
		

//##########################################################################################################################################################################	
//################################################################## Configuration Functions ########################################################################################		
	@AfterTest
	public void reportTestResult() throws Throwable {
		/**/fns_ReportTestResult_atClassLevel(IssueMgt_Suitexls,  (this.getClass().getSimpleName()) );
	}
	

	@AfterTest
	public void Test_success() throws Throwable{
		driver.quit();
	}
	
	
}	
	
	
	
	
	
	
	
