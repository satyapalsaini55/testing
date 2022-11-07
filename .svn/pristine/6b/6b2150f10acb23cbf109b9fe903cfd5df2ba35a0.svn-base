package nsf.ecap.NSFOnline_Suite;


import java.text.DecimalFormat;
import java.util.List;

import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class NSFOnline_Verify_CARs_audits extends TestSuiteBase_NSFOnline{
	
	public boolean NSFOnlineVersionScreenshotFlag = true;
	public boolean fail = false;
	public boolean skip = true;
	public boolean ValueNotMatched = true;
	public Integer count = -1;
	public Integer RowCount;
	
		
	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		count = -1;
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	

	
	
	

@Test( priority = 0)
public void Browser_Launch_and_Login_into_Insight_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS-04] NSFOnline Verify CAR Summary and Audit Information Test");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}
	
	
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void Insight_SearchCustomer_and_NsfOnline_Verify_CARs_Audits__(String Insight_Customer_No,String Insight_SearchUserName, String Multi_Access) throws Exception{
		
		
		
		Customer_No = (Insight_Customer_No.split("\\=")[1]).trim();
		String UserName = (Insight_SearchUserName.split("\\=")[1]).trim();
		String RecordYear = "2014";
		
		boolean customer_6M330 = false;
		boolean customer_C0002618 = false;
		boolean ExcellentFlag = false;
		boolean PassFlag = false;
		boolean FailFlag = false;
		boolean GoodFlag = false;
		boolean CompliesFlag = false;
		boolean Approved = true;
		boolean Overdue = true;
		
		
		String PaginationText = "";
		String CARSummary_ApprovedTextString = "";
		
		
		Integer AccountSummary_TotalCARs = 0;
		Integer CarGraphTotal=0;
		Integer Good = 0;
		Integer Excellent = 0;
		Integer Pass = 0;
		Integer Fail = 0;
		Integer Complies = 0;
		
		Integer CAR_x=0;
		Integer CAR_y=0;
		Integer CARSummary_Approved=0;
		Integer CARSummary_Overdue=0;
		
		
		count++;
		
		
		try {
					
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" for CustomerNo["+Customer_No+"].");
				
				if(Customer_No.contains("6M330")){
					customer_6M330 = true;
				}
				
				if(Customer_No.contains("C0002618")){
					customer_C0002618 = true;
				}
				
				try{
					CustomerName = fnsInsight_SerchCustomer_UserLinkClick(Customer_No, UserName);
				}catch(Throwable t){
					driver.quit();
					IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
					throw new Exception("Insight Error : "+Throwables.getStackTraceAsString(t));
				}
				
				fncSwitchingTo_NsfOnline_from_Insight_AfterClickingOnUserName();
				
				// New Code 9.2.2016			due to jira - NOM-1503, New Code has been added.	
				fnsSwitchViewAccount_Functionality(Customer_No, CustomerName, Multi_Access.trim());	
				
				try{
					if(NSFOnlineVersionScreenshotFlag){
						fncNsfOnline_Version_Screenshot("CARs_Customer_"+Customer_No);
						NSFOnlineVersionScreenshotFlag = false;
					}
				}catch(Throwable t){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			} 
				
			
			
			//Case3 Added on 21.9.2016
			if(Customer_No.contains("C0006292")){
				try{
					try{
						assert (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_MySnapShot_TAB_Header"))).size()>0) : "FAILED == 'My Sanpshot' TAB is NOT coming as default TAB on Home screen, Please refer screen shot >> MySnapshot_Tab_Not_Coming_DeafaultTab"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == 'My Sanpshot' TAB is coming as default TAB on Home screen.");	
					}catch(Throwable I){
						fnsTake_Screen_Shot("MySnapshot_Tab_Not_Coming_DeafaultTab");
					}
					
					try{
						assert (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_MySnapShot_TAB_AccountSummary_Section_Header"))).size()>0) : "FAILED == 'Account Summary' section is not coming under 'My Sanpshot' TAB, Please refer screen shot >> AccountSummary_Section_Not_Coming"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == 'Account Summary' section is coming under 'My Sanpshot' TAB.");	
					}catch(Throwable I){
						fnsTake_Screen_Shot("AccountSummary_Section_Not_Coming");
					}
					
					
					try{
						assert (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_MySnapShot_TAB_CorrectiveActionInformation_Section_Header"))).size()>0) : "FAILED == 'Corrective Action Information' section is not coming under 'My Sanpshot' TAB, Please refer screen shot >> CorrectiveActionInformation_Section_Not_Coming"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == 'Corrective Action Information' section is coming under 'My Sanpshot' TAB.");	
					}catch(Throwable I){
						fnsTake_Screen_Shot("CorrectiveActionInformation_Section_Not_Coming");
					}
					
					try{
						assert (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_MySnapShot_TAB_ScoreComparison_Section_Header"))).size()>0) : "FAILED == 'Score Comparison' section is not coming under 'My Sanpshot' TAB, Please refer screen shot >> ScoreComparison_Section_Not_Coming"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == 'Score Comparison section' is coming under 'My Sanpshot' TAB.");	
					}catch(Throwable I){
						fnsTake_Screen_Shot("ScoreComparison_Section_Not_Coming");
					}
					
					
					
					
					
					
					
					
					
					
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("My Snapshot :::::::::: "+Throwables.getStackTraceAsString(I));	}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			else{
				fnsGet_Element_Enabled("NSFOnline_SQFAudits_LeftPan_TAB");
				fnsWait_and_Click("NSFOnline_SQFAudits_LeftPan_TAB");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
				
				try{
					//Change the "All Rounds" dropdown(to the right) to select 2014 and click on Go.			
					
					if(customer_6M330){
						RecordYear = "2013";
					}
					
					
					ValueNotMatched = true;
					fnsGet_Element_Enabled("NsfOnline_Home_AllRound_DD");
					WebElement AllRoundDD=fnsGet_OR_NsfOnline_ObjectX("NsfOnline_Home_AllRound_DD");
					List<WebElement> AllRoundDD_ElementList=AllRoundDD.findElements(By.tagName("option"));
					for(WebElement AllRoundDD_Elements:AllRoundDD_ElementList){
						if((AllRoundDD_Elements.getText()).contains(RecordYear)){
							AllRoundDD_Elements.click();							
							ValueNotMatched=false;
							break;
						}
					}
					if(ValueNotMatched==true){
						fnsTake_Screen_Shot("DDSelectValueFail");
						throw new Exception("FAILED == Excel value<"+RecordYear+"> is not found in 'AllRound' DropDown, Please refer screen shot >> DDSelectValueFail"+fnsScreenShot_Date_format());
					}
					fnsApps_Report_Logs("PASSED == Successfully select the value<<"+RecordYear+"> from Drop");
					Thread.sleep(500);
					
					
					
					fnsWait_and_Click("NsfOnline_Go_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
					
					
					//CAR summary > click on the Approved CAR actions link.
					WebElement CarSummary=fnsGet_OR_NsfOnline_ObjectX("NsfOnline_CARSummary_FirstGraph_DivXpath");
					List<WebElement> CarSummaryElements=CarSummary.findElements(By.tagName("area"));
				
					
										
					int i=0;
					for(WebElement Elements:CarSummaryElements){
						
						
						
						String CARSummary_MouseOverAttributeTextArray=(CarSummaryElements.get(i).getAttribute("onmouseover"));
								//System.out.println("(CARSummary_MouseOverAttributeTextArray.toString())=========="+(CARSummary_MouseOverAttributeTextArray.toString()));			
						if(CARSummary_MouseOverAttributeTextArray.contains("Approved")){
							if(Approved){
								String[] CARSummary_ApprovedTextArray=(CarSummaryElements.get(i).getAttribute("onmouseover")).split("\\,");
								CARSummary_ApprovedTextString=CARSummary_ApprovedTextArray[2].toString();
								CARSummary_ApprovedTextString=CARSummary_ApprovedTextString.split("\\'")[1];
								CARSummary_ApprovedTextString=CARSummary_ApprovedTextString.split("Approved")[0].trim();
								CARSummary_Approved = Integer.parseInt(CARSummary_ApprovedTextString);
								System.out.println("CARSummary_ApprovedTextString="+CARSummary_ApprovedTextString);
							
							
								//System.out.println("Elements Location"+i+"="+(Elements.getLocation()));
								
								Point CAR_xy=Elements.getLocation();
								//System.out.println("Point="+CAR_xy);
								//System.out.println("");
								CAR_x=CAR_xy.getX();
								CAR_y=CAR_xy.getY();
								//System.out.println("x="+CAR_x+" y="+CAR_y);
							
								
								Approved = false;
								
								/*break;*/
								}
						}
						
						
						if(CARSummary_MouseOverAttributeTextArray.contains("Overdue")){
							if(Overdue){
									String[] CARSummary_OverdueTextArray=(CarSummaryElements.get(i).getAttribute("onmouseover")).split("\\,");
								String CARSummary_OverdueTextString=CARSummary_OverdueTextArray[2].toString();
								CARSummary_OverdueTextString=CARSummary_OverdueTextString.split("\\'")[1];
								CARSummary_OverdueTextString=CARSummary_OverdueTextString.split("Overdue")[0].trim();
								CARSummary_Overdue = Integer.parseInt(CARSummary_OverdueTextString);
								System.out.println("CARSummary_OverdueTextString="+CARSummary_OverdueTextString);
							
								Overdue = false;
								
								/*break;*/
								
							}
						}
						
						
						
						
						
						i++;
					}
					
	
					CarGraphTotal = CARSummary_Approved+CARSummary_Overdue;
					try{
						assert CarGraphTotal>0:"FAILED == CAR Summary Graph is not displayed, please refer screenshot >>CAR_Graph_Display_Fail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == CAR Summary Graph is displayed, CarGraphTotal("+CarGraphTotal+") from graph.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("CAR_Graph_Display_Fail");
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
					
					
					
					//Clicking on Approved Graph
					String NsfOnline_CARSummary_Graph_Approved_Click_Xpath = "(//area[contains(@onmouseover, '"+CARSummary_Approved+" Approved Corrective Actions')])[1]";
									
					Thread.sleep(1500);
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse = (JavascriptExecutor) driver;
					WebElement Graph_Approved_Link = driver.findElement(By.xpath(NsfOnline_CARSummary_Graph_Approved_Click_Xpath));
					jse.executeScript("arguments[0].click();", Graph_Approved_Link);
					
									
					//Actions act5=new Actions(driver);
					//act5.moveToElement(CarSummary, 0, 0).moveToElement(CarSummary, CAR_x, (CAR_y/2)).click().build().perform();
					//act5.moveToElement(CarSummary, CAR_x, CAR_y).click().build().perform(); // Not working on Chrome
					
					
					fnsApps_Report_Logs("PASSED == Successfully click on 'Approved Corrective Action' of the Graph.");
					Thread.sleep(6000);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					
					
					fnsGet_Element_Enabled("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
					WebElement PaginationDD=fnsGet_OR_NsfOnline_ObjectX("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
					List<WebElement> PaginationDD_ElementList=PaginationDD.findElements(By.tagName("option"));
					for(WebElement PaginationDD_EleList:PaginationDD_ElementList){
						if((PaginationDD_EleList.getText()).contains("Show")){
							PaginationText=(PaginationDD_EleList.getText().split("All")[1]).trim();
							
							
							break;
						}
					}
					fnsMove_To_MouseOver("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
					Thread.sleep(1000);
					try{
						assert CARSummary_ApprovedTextString.equalsIgnoreCase(PaginationText):"FAILED == CAR Summary Graph Count("+CARSummary_ApprovedTextString+") is not Matched with Pagination DropDown Count("+PaginationText+"), Please refer screen shot >> PaginationCountMatchFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == CAR Summary Graph Count("+CARSummary_ApprovedTextString+") is Matched with Pagination DropDown Count("+PaginationText+").");
					}catch(Throwable I){
						fnsTake_Screen_Shot("PaginationCountMatchFail");
					//	fnsApps_Report_Logs(I.getMessage());
						throw new Exception(Throwables.getStackTraceAsString(I));					}
				
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("CARs Graph :::::::::: "+Throwables.getStackTraceAsString(I));	}
				
				
			
					
	
					
				try{
					//Click on the Home link > Verify that Total CARs under the account summary and Approved CAR actions count is same. 
					fnsGet_Element_Enabled("NSFOnline_HomeMenu");
					fnsWait_and_Click("NSFOnline_HomeMenu");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					String AccountSummary_TotalCARsString=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Total CARs").trim();
					AccountSummary_TotalCARs = Integer.parseInt(AccountSummary_TotalCARsString);
					
					try{
						assert CarGraphTotal.equals(AccountSummary_TotalCARs):"FAILED == Account Summary Total CARs("+AccountSummary_TotalCARs+") is not matched with Graph CAR Summary Total("+CarGraphTotal+") on the graph, Please refer screen shot >> TotalCarsCountMatchFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Account Summary Total CARs("+AccountSummary_TotalCARs+") is matched with Graph CAR Summary Total("+CarGraphTotal+") on the graph.");
					}catch(Throwable I){
						fnsTake_Screen_Shot("TotalCarsCountMatchFail");	
						throw new Exception(Throwables.getStackTraceAsString(I));					}
					
					
					
					//Go to the 'Top 10 Non Compliance' and verify the top 3 values for % NC. Formula  %NC = (NC Count /  Total) * 100 %
					fnsWait_and_Click("NsfOnline_Top10NonCompliance_Tab");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					Integer Top10NonCompliance_Total_ColumnNo=fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow", "Total", "th");
					Integer Top10NonCompliance_NCCount_ColumnNo=fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow", "NC Count", "th");
					Integer Top10NonCompliance_PercentageNC_ColumnNo=fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow", "% NC", "th");
					
					for(int RowNo=2; RowNo<5; RowNo++){
						String TotalColumnTextXpath=OR_NsfOnline.getProperty("NSFOnline_Top10NonCompliance_Result_TableHeader")+"/tbody/tr["+RowNo+"]/td["+Top10NonCompliance_Total_ColumnNo+"]";
						String NCCountColumnTextXpath=OR_NsfOnline.getProperty("NSFOnline_Top10NonCompliance_Result_TableHeader")+"/tbody/tr["+RowNo+"]/td["+Top10NonCompliance_NCCount_ColumnNo+"]";
						String PercentageNCColumnTextXpath=OR_NsfOnline.getProperty("NSFOnline_Top10NonCompliance_Result_TableHeader")+"/tbody/tr["+RowNo+"]/td["+Top10NonCompliance_PercentageNC_ColumnNo+"]";
						
						String TotalColumnText=	TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(TotalColumnTextXpath).trim();
						Integer Total=Integer.parseInt(TotalColumnText);
						String NCCountColumnText=	TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NCCountColumnTextXpath).trim();
						Integer NCCount=Integer.parseInt(NCCountColumnText);
						String PercentageNC=	TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(PercentageNCColumnTextXpath).trim();
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(PercentageNCColumnTextXpath);
						
						//Formula
						DecimalFormat dfWithoutDec = new DecimalFormat("#");
						double PercentageNcCountInt = (double) (NCCount*100)/Total;
						String PercentageNcCount = dfWithoutDec.format(PercentageNcCountInt);
						
						try{
							assert PercentageNC.equalsIgnoreCase(PercentageNcCount):"FAILED == % NC count("+PercentageNC+") is not matched with count("+PercentageNcCount+") calculated from formula [% NC = (NC_Count/Total)*100], Please refer screen shot >> PercentageNcCountMatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == % NC count("+PercentageNC+") is matched with count("+PercentageNcCount+") calculated from formula [% NC = (NC_Count/Total)*100]");
						}catch(Throwable I){
							fnsTake_Screen_Shot("PercentageNcCountMatchFail");	
							throw new Exception(Throwables.getStackTraceAsString(I));					}
						
					}
					
				
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("Top10NonCompliance"+Throwables.getStackTraceAsString(I));	}
					
					
				
					
					
					
					
					
				//Audit Information Graph and TABS
				try{
					fnsMove_To_MouseOver("Footer");
					
					fnsGet_Element_Enabled("NsfOnline_CARSummary_SecondGraph_DivXpath");
					WebElement AuditInfo_Grades=fnsGet_OR_NsfOnline_ObjectX("NsfOnline_CARSummary_SecondGraph_DivXpath");
					List<WebElement> AuditInfo_GradesElements=AuditInfo_Grades.findElements(By.tagName("area"));
				
					int K=0;
					for(WebElement Elements:AuditInfo_GradesElements){
						
						String AuditInfo_Grades_MouseOverAttributeTextArray=(AuditInfo_GradesElements.get(K).getAttribute("onmouseover"));
											
						//Fetching Good Count from Graph
						if((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Good")){
							String[] AuditInfo_Grades_GoodTextArray=(AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
							String AuditInfo_Grades_Good=AuditInfo_Grades_GoodTextArray[2].toString();
							AuditInfo_Grades_Good=AuditInfo_Grades_Good.split("\\'")[1];
							AuditInfo_Grades_Good=(AuditInfo_Grades_Good.split("Good")[0]).trim(); 
							Good=Integer.parseInt(AuditInfo_Grades_Good);
							GoodFlag = true;
							System.out.println("AuditInfo_Grades_Good="+AuditInfo_Grades_Good);
						}
						
						//Fetching Excellent Count from Graph
						if((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Excellent")){
							String[] AuditInfo_Grades_ExcellentTextArray=(AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
							String AuditInfo_Grades_Excellent=AuditInfo_Grades_ExcellentTextArray[2].toString();
							AuditInfo_Grades_Excellent=AuditInfo_Grades_Excellent.split("\\'")[1];
							AuditInfo_Grades_Excellent=(AuditInfo_Grades_Excellent.split("Excellent")[0]).trim();  
							Excellent=Integer.parseInt(AuditInfo_Grades_Excellent);
							ExcellentFlag = true;
							System.out.println("AuditInfo_Grades_Excellent="+AuditInfo_Grades_Excellent);
						}
						
						//Fetching Pass Count from Graph
							if((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Pass")){
								String[] AuditInfo_Grades_PassTextArray=(AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
								String AuditInfo_Grades_Pass=AuditInfo_Grades_PassTextArray[2].toString();
								AuditInfo_Grades_Pass=AuditInfo_Grades_Pass.split("\\'")[1];
								AuditInfo_Grades_Pass=(AuditInfo_Grades_Pass.split("Pass")[0]).trim(); 
								Pass=Integer.parseInt(AuditInfo_Grades_Pass);
								PassFlag = true;
								System.out.println("AuditInfo_Grades_Pass="+AuditInfo_Grades_Pass);
							}
						
						
						//Fetching Fail Count from Graph
							if((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Fails")){
								
								String[] AuditInfo_Grades_FailTextArray=(AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
								String AuditInfo_Grades_Fail=AuditInfo_Grades_FailTextArray[2].toString();
								AuditInfo_Grades_Fail=AuditInfo_Grades_Fail.split("\\'")[1];
								AuditInfo_Grades_Fail=(AuditInfo_Grades_Fail.split("Fail")[0]).trim(); 
								Fail=Integer.parseInt(AuditInfo_Grades_Fail);
								FailFlag = true;
								System.out.println("AuditInfo_Grades_Fail="+AuditInfo_Grades_Fail);
							}
							
							//Fetching Complies Count from Graph
							if((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Complies")){
								
								String[] AuditInfo_Grades_CompliesTextArray=(AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
								String AuditInfo_Grades_Complies=AuditInfo_Grades_CompliesTextArray[2].toString();
								AuditInfo_Grades_Complies=AuditInfo_Grades_Complies.split("\\'")[1];
								AuditInfo_Grades_Complies=(AuditInfo_Grades_Complies.split("Complies")[0]).trim(); 
								Complies=Integer.parseInt(AuditInfo_Grades_Complies);
								CompliesFlag = true;
								System.out.println("AuditInfo_Grades_Complies="+AuditInfo_Grades_Complies);
							}
						
						K++;
					
					}
					
					
					
					//Percentage Count in javaCode
					DecimalFormat df = new DecimalFormat("#.00");
					
					Integer GradesGraphTotal = (Good+Excellent+Fail+Pass+Complies);
					
					
					
					if(ExcellentFlag){
						//% of Excellent  --> value should be same as in account summary.
						double Excellent_PercentageDouble=(double)(Excellent*100)/(GradesGraphTotal);
						String Excellent_Percentage = df.format(Excellent_PercentageDouble);
						
						String AccountSummary_Excellent=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Excellent");
						String AccountSummary_ExcellentSplit = AccountSummary_Excellent.split("\\.")[1];
						String AccountSummary_ExcellentText = "";
						if((AccountSummary_ExcellentSplit.length())<2){
							AccountSummary_ExcellentText = AccountSummary_Excellent+"0";
						}else{
							AccountSummary_ExcellentText=AccountSummary_Excellent;
						}
						
						try{
							assert Excellent_Percentage.equalsIgnoreCase(AccountSummary_ExcellentText):"FAILED == Graph Excellent_Percentage Count("+Excellent_Percentage+") is not matched with AccountSummary_Excellent Count("+AccountSummary_Excellent+"), Please refer screen shot >> ExcellentCountMatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Graph Excellent_Percentage Count("+Excellent_Percentage+") is Successfully matched with AccountSummary_Excellent Count("+AccountSummary_Excellent+").");
						}catch(Throwable I){
							fnsTake_Screen_Shot("ExcellentCountMatchFail");		
							throw new Exception(Throwables.getStackTraceAsString(I));					}
				}
					
						
						
					if(GoodFlag){	
						//% of Good  --> value should be same as in account summary.
						double Good_PercentageDouble = (double) (Good*100)/(GradesGraphTotal);
						String Good_Percentage = df.format(Good_PercentageDouble);
						
						String AccountSummary_Good=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Good");
						String AccountSummary_GoodSplit = AccountSummary_Good.split("\\.")[1];
						String AccountSummary_GoodText = "";
						if((AccountSummary_GoodSplit.length())<2){
							AccountSummary_GoodText = AccountSummary_Good+"0";
						}else{
							AccountSummary_GoodText=AccountSummary_Good;
						}
						
						try{
							assert Good_Percentage.equalsIgnoreCase(AccountSummary_GoodText):"FAILED == Graph Good_Percentage Count("+Good_Percentage+") is not matched with AccountSummary_Good Count("+AccountSummary_Good+"), Please refer screen shot >> GoodCountMatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Graph Good_Percentage Count("+Good_Percentage+") is Successfully matched with AccountSummary_Good Count("+AccountSummary_Good+").");
						}catch(Throwable I){
							fnsTake_Screen_Shot("GoodCountMatchFail");		
							throw new Exception(Throwables.getStackTraceAsString(I));					}
				}
					
					
					if(PassFlag){
						//% of Pass  --> value should be same as in account summary.
						double Pass_PercentageDouble = (double)(Pass*100)/(GradesGraphTotal);
						String Pass_Percentage = df.format(Pass_PercentageDouble);
					
					
						String AccountSummary_Pass=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Pass");
						String AccountSummary_PassSplit = AccountSummary_Pass.split("\\.")[1];
						String AccountSummary_PassText = "";
						if((AccountSummary_PassSplit.length())<2){
							AccountSummary_PassText = AccountSummary_Pass+"0";
						}else{
							AccountSummary_PassText=AccountSummary_Pass;
						}
						try{
							assert Pass_Percentage.equalsIgnoreCase(AccountSummary_PassText):"FAILED == Graph Pass_Percentage Count("+Pass_Percentage+") is not matched with AccountSummary_Pass Count("+AccountSummary_Pass+"), Please refer screen shot >> PassCountMatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Graph Pass_Percentage Count("+Pass_Percentage+") is Successfully matched with AccountSummary_Pass Count("+AccountSummary_Pass+").");
						}catch(Throwable I){
							fnsTake_Screen_Shot("PassCountMatchFail");		
							throw new Exception(Throwables.getStackTraceAsString(I));					}
					}
					
					
					if(FailFlag){
						//% of Fail  --> value should be same as in account summary.
						double Fail_PercentageDouble = (double)(Fail*100)/(GradesGraphTotal);
						String Fail_Percentage = df.format(Fail_PercentageDouble);
						
					
						String AccountSummary_Fail=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Fail");
						String AccountSummary_FailSplit = AccountSummary_Fail.split("\\.")[1];
						String AccountSummary_FailText = "";
						if((AccountSummary_FailSplit.length())<2){
							AccountSummary_FailText = AccountSummary_Fail+"0";
						}else{
							AccountSummary_FailText=AccountSummary_Fail;
						}
						try{
							assert Fail_Percentage.equalsIgnoreCase(AccountSummary_FailText):"FAILED == Graph Fail_Percentage Count("+Fail_Percentage+") is not matched with AccountSummary_Fail Count("+AccountSummary_Fail+"), Please refer screen shot >> FailCountMatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Graph Fail_Percentage Count("+Fail_Percentage+") is Successfully matched with AccountSummary_Fail Count("+AccountSummary_Fail+").");
						}catch(Throwable I){
							fnsTake_Screen_Shot("FailCountMatchFail");		
							throw new Exception(Throwables.getStackTraceAsString(I));					}
					}
					
					if(CompliesFlag){
						//% of Complies  --> value should be same as in account summary.
						double Complies_PercentageDouble = (double)(Complies*100)/(GradesGraphTotal);
						String Complies_Percentage = df.format(Complies_PercentageDouble);
						
					
						String AccountSummary_Complies=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Complies");
						String AccountSummary_CompliesSplit = AccountSummary_Complies.split("\\.")[1];
						String AccountSummary_CompliesText = "";
						if((AccountSummary_CompliesSplit.length())<2){
							AccountSummary_CompliesText = AccountSummary_Complies+"0";
						}else{
							AccountSummary_CompliesText=AccountSummary_Complies;
						}
						try{
							assert Complies_Percentage.equalsIgnoreCase(AccountSummary_CompliesText):"FAILED == Graph Complies_Percentage Count("+Complies_Percentage+") is not matched with AccountSummary_Complies Count("+AccountSummary_Complies+"), Please refer screen shot >> CompliesCountMatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Graph Complies_Percentage Count("+Complies_Percentage+") is Successfully matched with AccountSummary_Complies Count("+AccountSummary_Complies+").");
						}catch(Throwable I){
							fnsTake_Screen_Shot("CompliesCountMatchFail");		
							throw new Exception(Throwables.getStackTraceAsString(I));					}
					}
					
	
					
					//Verify that total of all graphs count under audit information is equal to the "Audits Performed"
					String AuditsPerformedText=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Audits Performed");
					Integer AuditsPerformed=Integer.parseInt(AuditsPerformedText);
					try{
						assert AuditsPerformed.equals(GradesGraphTotal):"FAILED == Graph Total Count("+GradesGraphTotal+") is not matched with AuditPerformed Count("+AuditsPerformed+"), Please refer screen shot >> GraphTotalCountMatchFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Graph Total Count("+GradesGraphTotal+") is Successfully matched with AuditPerformed Count("+AuditsPerformed+").");
						Thread.sleep(2000);
					}catch(Throwable I){
						fnsTake_Screen_Shot("GraphTotalCountMatchFail");		
						throw new Exception(Throwables.getStackTraceAsString(I));					}
					
				
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("Grades Graph "+Throwables.getStackTraceAsString(I));	} 
				
					
					
					
					
					
					
					
					
					
					
				try{
					//Go to the Top 10>Verify that the First value of Current Round should be equal to the Top Score of the Account summary.
					fnsGet_Element_Enabled("NSFOnline_AuditInfo_Top10_Tab");
					fnsWait_and_Click("NSFOnline_AuditInfo_Top10_Tab");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
									
					
					//fnsGet_Element_Enabled("Footer");
					//fnsMove_To_MouseOver("Footer");
					if(customer_6M330){
						fnsGet_Element_Enabled("NSFOnline_AuditInfo_Top10_Go_Bttn");		
					}
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					//Thread.sleep(2000);
										
					fnsGet_Element_Enabled("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell");
					String CurrentRound=fnsGet_Field_TEXT("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell").trim();
					CurrentRound=CurrentRound.split("\\%")[0];
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell"));
					//fnsMove_To_Element_and_DoubleClick("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell");
					String TopScore=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Top Score");
					TopScore=TopScore.split("\\.")[0];
					try{
						assert CurrentRound.equalsIgnoreCase(TopScore):"FAILED == CurrentRound("+CurrentRound+"%) is not matched with AccountSummary TopScore("+TopScore+"%), Please refer screen shot >> TopScoreMatchFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Successfully Matched Current Round("+CurrentRound+"%) with AccountSummary TopScore("+TopScore+"%).");
						Thread.sleep(1500);
					}catch(Throwable I){
						fnsTake_Screen_Shot("TopScoreMatchFail");	
						throw new Exception(Throwables.getStackTraceAsString(I));					}
				
	
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("AuditInfo Top10 "+Throwables.getStackTraceAsString(I));	} 
					
					
					
					
					
					
					
					
					
					
				try{	
					
			
					//Go to the Most Improved>Verify that the First value of Current Round should be greater than the Previous Round.
					fnsGet_Element_Enabled("NSFOnline_AuditInfo_MostImproved_Tab");
					fnsWait_and_Click("NSFOnline_AuditInfo_MostImproved_Tab");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					//fnsGet_Element_Enabled("Footer");
					//fnsMove_To_MouseOver("Footer");
					if(customer_6M330){
						fnsGet_Element_Enabled("NSFOnline_AuditInfo_MostImproved_Go_Bttn");
					}
					
					fnsGet_Element_Enabled("NSFOnline_AuditInfo_MostImproved_TableHeader");
						
										
					for(int row=2; row<25; row++){
						String CurrentRoundColumnxpath=(OR_NsfOnline.getProperty("NSFOnline_AuditInfo_MostImproved_TableHeader")+"/tbody/tr["+row+"]/td[3]");
						String PreviousRoundColumnxpath=(OR_NsfOnline.getProperty("NSFOnline_AuditInfo_MostImproved_TableHeader")+"/tbody/tr["+row+"]/td[4]");
						//Thread.sleep(400);
						if(driver.findElements(By.xpath(CurrentRoundColumnxpath)).size()>0){
							
							//Table Current Round Data Fetch
							String CurrentRoundColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(CurrentRoundColumnxpath).trim();
							CurrentRoundColumnText = CurrentRoundColumnText.split("\\%")[0];
							Integer CurrentRoundColumn = Integer.parseInt(CurrentRoundColumnText);
														
							//Table Previous Round Data Fetch
							String PreviousRoundColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(PreviousRoundColumnxpath).trim();
							PreviousRoundColumnText = PreviousRoundColumnText.split("\\%")[0];
							Integer PreviousRoundColumn = Integer.parseInt(PreviousRoundColumnText);
							
							TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(CurrentRoundColumnxpath);
							TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(PreviousRoundColumnxpath);
							try{
								assert CurrentRoundColumn>PreviousRoundColumn:"FAILED == Current Round("+CurrentRoundColumn+") is not Greater than from Previous Round("+PreviousRoundColumn+") for Table Row("+row+"), Please refer screen shot >> GreaterThanMatchFail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully Verify Current Round("+CurrentRoundColumn+") is Greater than from Previous Round("+PreviousRoundColumn+") for Table Row("+row+").");
							}catch(Throwable I){
								fnsTake_Screen_Shot("GreaterThanMatchFail");		
								throw new Exception(Throwables.getStackTraceAsString(I));					}
						}else{
							break;
						}
					}
					
	
			}catch(Throwable I){
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				throw new Exception("AuditInfo MostImproved "+Throwables.getStackTraceAsString(I));	} 
							
						
					
					
					
					
					
					
					
					
					
				try{
					
					//Go to the Bottom 10>Verify that the First value of Current Round should be equal to the Bottom Score of the Account summary.
					fnsGet_Element_Enabled("NSFOnline_AuditInfo_Bottom10_Tab");
					fnsWait_and_Click("NSFOnline_AuditInfo_Bottom10_Tab");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
	
				//	fnsGet_Element_Enabled("Footer");
				//	fnsMove_To_MouseOver("Footer");
					if(customer_6M330){
						fnsGet_Element_Enabled("NSFOnline_AuditInfo_Bottom10_Go_Bttn");
					}
					
				//	Thread.sleep(2000);
					
					fnsGet_Element_Enabled("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell");
					String BottomCurrentRound=fnsGet_Field_TEXT("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell").trim();
					BottomCurrentRound=BottomCurrentRound.split("\\%")[0];
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell"));
					//fnsMove_To_Element_and_DoubleClick("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell");
					String BottomScore=fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Bottom Score");
					BottomScore=BottomScore.split("\\.")[0];
					try{
						assert BottomCurrentRound.equalsIgnoreCase(BottomScore):"FAILED == BottomCurrentRound("+BottomCurrentRound+"%) is not matched with AccountSummary BottomScore("+BottomScore+"%), Please refer screen shot >> BottomScoreMatchFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Successfully Matched Bottom Current Round("+BottomCurrentRound+"%) with AccountSummary BottomScore("+BottomScore+"%).");
						Thread.sleep(1500);
					}catch(Throwable I){
						fnsTake_Screen_Shot("BottomScoreMatchFail");		
						throw new Exception(Throwables.getStackTraceAsString(I));					}
				
					
					
			
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("AuditInfo Bottom10"+Throwables.getStackTraceAsString(I));	} 
			
				
			
			
			
			
			
			
			
			
			
			
			
		}
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");


		}catch(SkipException sk){
			throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
}

	

	
	
	
	
	
	
	

	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
}

	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(NSFOnline_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	

	@AfterTest
	public void QuitBrowser(){
		try{
			MoveMouseCursorToTaskBar();
			driver.quit();
		}catch(Throwable t){
			//nothing to do
		}
	}
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(NSFOnline_suitexls, this.getClass().getSimpleName());
	}

	
	
}	
	
	
	
	
	
	
	
