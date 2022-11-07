package nsf.ecap.New_NSFOnline_Suite;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_16_Alerts_SortOder extends TestSuiteBase_New_NSFOnline {

	

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
public String CreateView_Step2_Country_DD = null;







@Test( priority = 1)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		CreateView_Step2_Country_DD = "United Kingdom";
		
		
		fnsApps_Report_Logs("################################## [BS-16]  Create View in Site and Snapshot and Verify Records Count");
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
public void Create_New_View_With_Different_Alerts_under_Site_Module() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_New_View_With_Different_Alerts_under_Site ");
	try{		
		fnsLoading_Progressing_wait(5); 
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_SearchSites_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Sites' Menu Ajax", "Sites");
		
		fncCreateNew_Views_with_Alerts("Automation - RED");
		fncCreateNew_Views_with_Alerts("Automation - ORANGE");
		fncCreateNew_Views_with_Alerts("Automation - GREEN");
		fncCreateNew_Views_with_Alerts("Automation - GRAY");
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 3, dependsOnMethods={"Create_New_View_With_Different_Alerts_under_Site_Module"}, description="")
public void Verify_Alerts_Generated_with_Correct_Color_under_Site_Section() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::3 Verify_Alerts_Generated_with_Correct_Color_under_Site_Section ");
	try{
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Alerts_Ajax");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait_AlertLaoder(3);
		
		fncVerify_Alert_Genrated_with_Correct_Color_Under_Alerts("Automation - RED");
		fncVerify_Alert_Genrated_with_Correct_Color_Under_Alerts("Automation - ORANGE");
		fncVerify_Alert_Genrated_with_Correct_Color_Under_Alerts("Automation - GREEN");
		fncVerify_Alert_Genrated_with_Correct_Color_Under_Alerts("Automation - GRAY");
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}








@Test( priority = 4, dependsOnMethods={"Verify_Alerts_Generated_with_Correct_Color_under_Site_Section"}, description="")
public void Verify_Alerts_Color_Sorting___Red_Orange_Green_Gray___under_Site_Section() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::4 Verify_Alerts_Color_Sorting___Red_Orange_Green_Gray___under_Site_Section ");
	String AlertColorValue_FromApps = null;
	boolean Color_Orange = false;
	boolean Color_green = false;
	boolean Color_gray = false;
	boolean Product_Label_Found = false;
	int Alert_No = 1;
	try{
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")[1]");
	
		Integer All_Products_Div_Size = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath"))).size();
		
		for(int Entity_No=1; Entity_No<=All_Products_Div_Size; Entity_No++){
			
			String Products_Div_Xpath = "("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")["+Entity_No+"]/div";
			if(driver.findElements(By.xpath(Products_Div_Xpath)).size()>0){
				String Products_Div_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Products_Div_Xpath).getText().trim();
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(Products_Div_Xpath);
				if(Products_Div_Text.toLowerCase().equals("sites")){
					Product_Label_Found = true;
					List<WebElement> AlertsColorLabel_Objs = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX("("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")["+Entity_No+"]").findElements(By.tagName("label"));
					for(WebElement AlertsColorLabel_elements : AlertsColorLabel_Objs){
						Actions act = new Actions(driver);
						act.moveToElement(AlertsColorLabel_elements).build().perform();
						AlertColorValue_FromApps =  AlertsColorLabel_elements.getCssValue("background-color").toLowerCase().trim();
						switch(AlertColorValue_FromApps){			
							case ("rgba(255, 78, 77, 1)") : {
								if(Color_Orange)
									throw new Exception ("FAILED == 'Orange' color alert is coming before RED color alert which is not expected, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
								else if(Color_green)
									throw new Exception ("FAILED == 'Green' color alert is coming before RED/ORANGE color alert which is not expected, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
								else if(Color_gray)
									throw new Exception ("FAILED == 'Gray' color alert is coming before RED/ORANGE/GREEN color alert which is not expected, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
							
								Color_Orange = false;
								Color_green = false;
								Color_gray = false;
								fnsApps_Report_Logs("PASSED == Successfully verified that the 'Red' color is displayed for Alert="+Alert_No+" > under site section.");
								break;
							}
							
							case ("rgba(254, 153, 0, 1)") : {
								if(Color_green)
									throw new Exception ("FAILED == 'Green' color alert is coming before RED/ORANGE color alert which is not expected, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
								else if(Color_gray)
									throw new Exception ("FAILED == 'Gray' color alert is coming before RED/ORANGE/GREEN color alert which is not expected, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
							
								Color_Orange = true;
								Color_green = false;
								Color_gray = false;
								fnsApps_Report_Logs("PASSED == Successfully verified that the 'Orange' color is displayed for Alert="+Alert_No+" > under site section.");
								break;
							}
							
							case ("rgba(131, 195, 129, 1)") : {
								if(Color_gray)
									throw new Exception ("FAILED == 'Gray' color alert is coming before RED/ORANGE/GREEN color alert which is not expected, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
							
								Color_green = true;
								Color_gray = false;
								fnsApps_Report_Logs("PASSED == Successfully verified that the 'Green' color is displayed for Alert="+Alert_No+" > under site section.");
								break;
							}
							
							case ("rgba(204, 204, 204, 1)") : {
								Color_gray = true;
								fnsApps_Report_Logs("PASSED == Successfully verified that the 'Gray' color is displayed for Alert="+Alert_No+" > under site section.");
								break;
							}	
							default : {
								throw new Exception("FAILED == Color Sorting failed as expected color (coming into the Alert) is not into the given list, please refer the screen shot >> Color_Sorting_FAIL"+fnsScreenShot_Date_format());
							}
						}
						Alert_No++;
							
					}
					
				}	
			}	
			if(Product_Label_Found){
				break;
			}
			
		}
		
		if(Product_Label_Found==false){
			throw new Exception("FAILED == 'sites' section is not found on alert screen, Please refer screen shot >> Alert_Record_Not_found"+fnsScreenShot_Date_format());
		}
			
		driver.quit();
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("Color_Sorting_FAIL");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 5, dependsOnMethods={"Verify_Alerts_Color_Sorting___Red_Orange_Green_Gray___under_Site_Section"}, description="")
public void Verify_Entity_Order_on_AlertsScreen() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::5 Verify_Entity_Order_on_AlertsScreen. ");
	try{
		String First_Entity_Name = "site";
		String Second_Entity_Name = "user";
		Integer Update_First_Entity_Order = null;
		Integer Update_Second_Entity_Order = null;
		boolean Entity_First_Have_LowerOrder = false;
		boolean Entity_Second_Have_LowerOrder = false;
		boolean Entity_First_Found = false;
		boolean Entity_Second_Found = false;
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		
		fnsLoading_Progressing_wait_AlertLaoder(2);
		Integer First_Entity_Order = fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query("select", First_Entity_Name, null);
		Integer Second_Entity_Order = fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query("select", Second_Entity_Name, null);
		Update_First_Entity_Order = Second_Entity_Order;
		Update_Second_Entity_Order = First_Entity_Order;
		
		if(First_Entity_Order<Second_Entity_Order){
			Entity_First_Have_LowerOrder = true;
		}else if(Second_Entity_Order<First_Entity_Order){
			Entity_Second_Have_LowerOrder = true;
		}else{
			throw new Exception("FAILED == Order are same for both entities '"+First_Entity_Name+"' and '"+Second_Entity_Name+"'.");
		}
		
		
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")[1]");
			Integer All_Products_Div_Size = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath"))).size();
			
			for(int Entity_No=1; Entity_No<=All_Products_Div_Size; Entity_No++){				
				String Products_Div_Xpath = "("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")["+Entity_No+"]/div";
				if(driver.findElements(By.xpath(Products_Div_Xpath)).size()>0){
					String Products_Div_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Products_Div_Xpath).getText().trim();
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(Products_Div_Xpath);
					if( (Products_Div_Text.toLowerCase().contains(First_Entity_Name.toLowerCase().trim())) && Entity_First_Have_LowerOrder  ){
						Entity_First_Found = true;
						Entity_First_Have_LowerOrder = false;
						Entity_Second_Have_LowerOrder = true;
					}else if( (Products_Div_Text.toLowerCase().contains(Second_Entity_Name.toLowerCase().trim())) &&  Entity_Second_Have_LowerOrder){
						Entity_Second_Found = true;
						Entity_First_Have_LowerOrder = true;
						Entity_Second_Have_LowerOrder = false;
					}
					
					if(Entity_First_Found && Entity_Second_Found){
						break;
					}							
				}					
			}
			
			if(Entity_First_Found==false ){
				throw new Exception("FAILED == '"+First_Entity_Name+"' entity is not found on alert screen, Please refer screen shot >> Alert_Record_Not_found"+fnsScreenShot_Date_format());
			}else if(Entity_Second_Found==false){
				throw new Exception("FAILED == '"+Second_Entity_Name+"' entity is not found on alert screen, Please refer screen shot >> Alert_Record_Not_found"+fnsScreenShot_Date_format());
			}else{
				fnsApps_Report_Logs("PASSED == Entities ("+First_Entity_Name+" and "+Second_Entity_Name+") are found and the order is correct.");
			}
				
			driver.quit();
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Color_Sorting_FAIL");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		Entity_First_Have_LowerOrder = false;
		Entity_Second_Have_LowerOrder = true;
		Entity_First_Found = false;
		Entity_Second_Found = false;
		fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query("update", First_Entity_Name, Update_First_Entity_Order);
		fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query("update", Second_Entity_Name, Update_Second_Entity_Order);
		
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")[1]");
			Integer All_Products_Div_Size = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath"))).size();
			
			for(int Entity_No=1; Entity_No<=All_Products_Div_Size; Entity_No++){				
				String Products_Div_Xpath = "("+OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath")+")["+Entity_No+"]/div";
				if(driver.findElements(By.xpath(Products_Div_Xpath)).size()>0){
					String Products_Div_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Products_Div_Xpath).getText().trim();
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(Products_Div_Xpath);
					if( (Products_Div_Text.toLowerCase().contains(First_Entity_Name.toLowerCase().trim())) && Entity_First_Have_LowerOrder  ){
						Entity_First_Found = true;
						Entity_First_Have_LowerOrder = false;
						Entity_Second_Have_LowerOrder = true;
					}else if( (Products_Div_Text.toLowerCase().contains(Second_Entity_Name.toLowerCase().trim())) &&  Entity_Second_Have_LowerOrder){
						Entity_Second_Found = true;
						Entity_First_Have_LowerOrder = true;
						Entity_Second_Have_LowerOrder = false;
					}
					
					if(Entity_First_Found && Entity_Second_Found){
						break;
					}						
				}					
			}
			
			if(Entity_First_Found==false ){
				throw new Exception("FAILED == '"+First_Entity_Name+"' entity is not found on alert screen, Please refer screen shot >> Alert_Record_Not_found"+fnsScreenShot_Date_format());
			}else if(Entity_Second_Found==false){
				throw new Exception("FAILED == '"+Second_Entity_Name+"' entity is not found on alert screen, Please refer screen shot >> Alert_Record_Not_found"+fnsScreenShot_Date_format());
			}else{
				fnsApps_Report_Logs("PASSED == Entities ("+First_Entity_Name+" and "+Second_Entity_Name+") are found and the order is correct.");
			}
				
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Color_Sorting_FAIL");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query("update", First_Entity_Name, First_Entity_Order);
		fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query("update", Second_Entity_Name, Second_Entity_Order);		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}





















//#######################################################  Class's Method  ################################################################

public void fncCreateNew_Views_with_Alerts(String View_Name) throws Throwable{
	try{		
		fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
		
		if(View_Name.toLowerCase().trim().contains("gray")){
			fnsGet_Element_Enabled("CreateView_Step2_StoreNo");
			fnsWait_and_Type("CreateView_Step2_StoreNo", "12345678");
		}else{
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Country", CreateView_Step2_Country_DD);
		}
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		if( (View_Name.toLowerCase().trim().contains("red")) || (View_Name.toLowerCase().trim().contains("gray")) ){
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsLoading_Progressing_wait(1);
		}else if(View_Name.toLowerCase().trim().contains("green")){
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_GREEN_Radio_bttn");
			fnsLoading_Progressing_wait(1);
		}else if(View_Name.toLowerCase().trim().contains("orange")){
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_ORANGE_Radio_bttn");
			fnsLoading_Progressing_wait(1);
		}
				
			
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
				
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "A new view has been added to your list", 25);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Sites");
				
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(View_Name+" : "+Throwables.getStackTraceAsString(t));
		throw new Exception(View_Name+" : "+Throwables.getStackTraceAsString(t));
	}
	
}





public void fncVerify_Alert_Genrated_with_Correct_Color_Under_Alerts(String View_Name) throws Throwable{
	try{
		fnsLoading_Progressing_wait_AlertLaoder(2);
		String Alert_Xpath = "//a[text()='"+View_Name+"']";	
		
		if(driver.findElements(By.xpath(Alert_Xpath)).size()>0){
			String Alert_ColorLabel_Xpath = Alert_Xpath+"/preceding-sibling::label";
			String Alert_Color_Code = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Alert_ColorLabel_Xpath).getCssValue("background-color").toLowerCase().trim();
			String View_Color =  View_Name.split("-")[1].toLowerCase().trim();
			
			switch(View_Color){			
				case ("red") : {
					assert Alert_Color_Code.equals("rgba(255, 78, 77, 1)"):"FAILED == Expected color (RED) is not coming for View '"+View_Name+"', please refer the screen shot >> Alert_nd_Color_Verification_FAIL"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Expected color (RED) is coming for View '"+View_Name+"'.");
					break;
				}
				
				case ("orange") : {
					assert Alert_Color_Code.equals("rgba(254, 153, 0, 1)"):"FAILED == Expected color (ORANGE) is not coming for View '"+View_Name+"', please refer the screen shot >> Alert_nd_Color_Verification_FAIL"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Expected color (ORANGE) is coming for View '"+View_Name+"'.");
					break;
				}
				
				case ("green") : {
					assert Alert_Color_Code.equals("rgba(131, 195, 129, 1)"):"FAILED == Expected color (GREEN) is not coming for View '"+View_Name+"', please refer the screen shot >> Alert_nd_Color_Verification_FAIL"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Expected color (GREEN) is coming for View '"+View_Name+"'.");
					break;
				}
				
				case ("gray") : {
					assert Alert_Color_Code.equals("rgba(204, 204, 204, 1)"):"FAILED == Expected color (GRAY) is not coming for View '"+View_Name+"', please refer the screen shot >> Alert_nd_Color_Verification_FAIL"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Expected color (GRAY) is coming for View '"+View_Name+"'.");
					break;
				}	
				default : {
					throw new Exception("FAILED == Expected color is not mentioned into the ViewName, please correct the view name.");
				}			
			}
			
		}else{
			throw new Exception("FAILED == Alert '"+View_Name+"' is not present under site section, please refer the screen shot >> Alert_nd_Color_Verification_FAIL"+fnsScreenShot_Date_format());
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("Alert_nd_Color_Verification_FAIL");
		fnsApps_Report_Logs(View_Name+" : "+Throwables.getStackTraceAsString(t));
		throw new Exception(View_Name+" : "+Throwables.getStackTraceAsString(t));
	}
	
}





//Function to run DB Select/Update queries for fetching default screen name.
public Integer fnsDB_Fetch_or_Update_Alerts_EntityOrder_Query(String ScriptRunForWhich__SelectUpdate, String EntityName, Integer Update_EntityOrder) throws Throwable {
	Connection connection = null;
	Integer Return_EntityOrder = null;
	String	EntityOrder = "";
	try{
		fnsApps_Report_Logs("=========================================================================================================================================");
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		    
		
		connection = fnpGetDBConnection(); 
		 
		Statement stmt= connection.createStatement();
		
		switch(ScriptRunForWhich__SelectUpdate) {
		
			case ("select") : {
				String Select_query="select distinct display_order from cw_vo where vo_name='"+EntityName+"'";
				ResultSet rs = stmt.executeQuery(Select_query);
				
				while(rs.next()){
					EntityOrder = rs.getString("display_order").trim();
					break;
				}
				fnsApps_Report_Logs("**** Select Query Executed Successfully and order no is = ("+Return_EntityOrder+"). Query is >> "+Select_query); 	
				Return_EntityOrder = Integer.parseInt(EntityOrder);
				assert ( !(Return_EntityOrder==null ) ): 
					"FAILED == Entity ("+EntityName+") order is not mentioned into the DB. Query is >> "+Select_query;
				break;							
			}
			
			case ("update") : {
				String Update_Query ="update cw_vo set display_order ="+Update_EntityOrder+" where vo_name='"+EntityName+"'";
				stmt.executeUpdate(Update_Query);
				connection.commit();
				fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 				
				break;
			}
			
		}
		connection.close();
		   
	   
	}catch (SQLException e) {
		fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		throw new Exception ("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
	}finally {
		if( !(connection==null) ){
			connection.close();
		}
	}
		fnsApps_Report_Logs("=========================================================================================================================================");
		System.out.println("DB Value = "+Return_EntityOrder);
	return Return_EntityOrder;
}	


//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}