package nsf.ecap.Agriculture_suite;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;

//BS-03
public class Create_Membership_With_Option2 extends TestSuiteBase_Agriculture_suite {
	
	
	//SoftAssert softAssert = null ;
	SoftAssert softAssert ;
	Hashtable<String, String> ht = new Hashtable<String,String>();
	
	String productInFirstRow;
	String pathtoCopy;
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		
		CM_BS01 = new Create_Membership();
		CM_BS01.checkTestSkip(this.getClass().getSimpleName());

		//hashXlData.clear();
	

	}

	 @Test(priority = 1)

	public void Create() throws Throwable {
		// start = new Date();//This start  time here refer from created membership onwards
		 setStartDate();
		 
		 try{
			 
			 	String textMessage;
			 
			 	fnpCommonOption2_code_from_Import_Till_CorrespondingWO_created();
			 	
			 	
/*
				String woNo = fnpGetText_OR("WoLinkAtImportResultsScreen");
				fnpMymsg("Wo no. is ---"+woNo);
				fnpClick_OR("WoLinkAtImportResultsScreen");
				fnpWaitForVisible("iAg_SnapshotTabLink");
				
				
				
				

				
				String path=System.getProperty("user.dir") + "\\docs\\iAg_option2\\ModifiedThoroughScript\\"+iAgOption2FileName;
				Xls_Reader option2Excel=new Xls_Reader(path);
				
				
				
				*//**************Producer Data looks like*****************************************************************************//*
				
				fnpClick_OR("iAg_ProducersTabLink");
				int producersRow = fnpCountRowsInTable("ProducersTab_Table_data");
				
				String currentWorkingSheet="Producers";

				int producerNameStartRowIndex=13;
				int producerNameStartColIndex=4;
				String producerNameColValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet,producerNameStartColIndex,producerNameStartRowIndex);
				if (producerNameColValue.equalsIgnoreCase("*Producer Name")) {
					fnpMymsg("Producer Name column is present.");
				} else {
					throw new Exception("Producer Name column is not present at row index '"+producerNameStartRowIndex+"' and col index '"+producerNameStartColIndex+"' . It means excel has been changed or new or corrupted.");
				}

				

				
				int jProducerRowCount=0;
				String temp;
				for(int i=(producerNameStartRowIndex+1); i <= option2Excel.getRowCount(currentWorkingSheet) ;i++ ){
					
					temp=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, producerNameStartColIndex, i).trim();
					if(temp.equalsIgnoreCase("")){
						break;
					}else{
						jProducerRowCount++;
					}
				}
				System.out.println("Total rows are in excel in Producer sheet--"+jProducerRowCount);
				fnpMymsg("Total rows are in excel in Producer sheet--"+jProducerRowCount);
				fnpMymsg("Total Producers in excel are ---"+jProducerRowCount);
				fnpMymsg("Total Producers in iPulse for current work order are ---"+producersRow);
				if (jProducerRowCount!=producersRow){
					throw new Exception("Total count of Producers '"+jProducerRowCount+"' in excel are not matched with totalcount of Producers '"+producersRow+"'  in site");
				}else{
					fnpMymsg("Total count of Producers '"+jProducerRowCount+"' in excel are  matched with totalcount of Producers '"+producersRow+"'  in site");
				}
				
				
				
				String firstProducerName="";
				String tmpExcelValue;
				String tmpSiteValue;
				int nameColNo=fnpFindColumnIndex("ProducersTab_Table_HeaderRow", "Name");
				for(int i=1; i <= 5;i++ ){
					tmpExcelValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, producerNameStartColIndex, (producerNameStartRowIndex+i)).trim();
					tmpSiteValue=fnpFetchFromTable("ProducersTab_Table_data", i, nameColNo);
					if(tmpExcelValue.equalsIgnoreCase(tmpSiteValue)){
						fnpMymsg("Name in '"+i+"' row is matched with excel i.e. in excel it is --'"+tmpExcelValue+"' and in site it is --'"+tmpSiteValue+"'.");
						if (i==1) {
							firstProducerName=tmpExcelValue;
						}
					}else{
						fnpMymsg("Name in '"+i+"' row is NOT matched with excel. Value in excel is this '"+tmpExcelValue+"' but in site it is '"+tmpSiteValue+"'.");
						throw new Exception("Name in '"+i+"' row is NOT matched with excel. Value in excel is this '"+tmpExcelValue+"' but in site it is '"+tmpSiteValue+"'.");
					}
				}
				
				
				
				
				
				
				String firstProducerName="";
				int nameColIndex = fnpFindColumnIndex("ProducersTab_Table_HeaderRow", "Name");
				String name ;
				String temp=null;
				boolean found=false;
				for(int i=0; i <producersRow ;i++ ){
					found=false;
					 name = fnpFetchFromTable("ProducersTab_Table_data", (i+1), nameColIndex);
					 fnpMymsg("Producer at row no. '"+(i+1)+"' is--'"+name+"'.");
						for(int j=(producerNameStartRowIndex+1); j <= option2Excel.getRowCount(currentWorkingSheet) ;j++ ){							
							 temp = option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, producerNameStartColIndex, j).trim();
							if(temp.equalsIgnoreCase(name)){
								found=true;
								fnpMymsg("This producer '"+name+"' is present in excel at row no. --'"+j+"'.");
								break;
							}
						}
						
						if (!(found)) {
							msg="This producer '"+name+"' is NOT present in any row in excel sheet '"+currentWorkingSheet+"'.";
							fnpMymsg(msg);
							throw new Exception(msg);
							
						} 
						if (i==0) {//This first producer name will be use for comparison while comparing first site of apple in Product & Coverage tab
							firstProducerName=temp;
						}

				}
				
				
				
				
				
				
				
				
				
				

				
				*//************************************************************************************************//*
				
				
				
				
				
				
				*//**************Sites data looks like*****************************************************************************//*
				//here we are just count no. of rows and count should be 7
				
				fnpClick_OR("iAg_SitesTabLink");
				int sitesRow = fnpCountRowsInTable("SitesTab_Table_data");
				
				fnpMymsg("Total Sites rows in iPulse for current work order are ---"+sitesRow);
				int expectedSitesRows=10;
				if (sitesRow!=expectedSitesRows){
					throw new Exception("Total count of rows in Sites are '"+sitesRow+"' but they must be exact "+expectedSitesRows+" rows .");
				}else{
					fnpMymsg("Total count of rows in Sites are '"+sitesRow+"'.");
				}
				
				
				*//*************Top 5 sites present in excel should be present at sites tab of application*********************//*
				String sitesName="";
				int nameColIndex_sitesTab = fnpFindColumnIndex("SitesTab_Table_HeaderRow", "Name");
				temp=null;
				found=false;
				int sitesNameStartRowIndex=14;
				int sitesNameStartColIndex=6;
				currentWorkingSheet="Sites";
				for(int j=(sitesNameStartRowIndex); j < (sitesNameStartRowIndex+5) ;j++ ){	
					found=false;
					sitesName = option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, sitesNameStartColIndex, j).trim();
					 
					 fnpMymsg("Sites (in excel) at row no. '"+(j)+"' is--'"+sitesName+"'.");
						for(int m=1; m <= sitesRow ;m++ ){							
							temp = fnpFetchFromTable("SitesTab_Table_data", (m), nameColIndex_sitesTab);
							if(sitesName.equalsIgnoreCase(temp)){
								found=true;
								fnpMymsg("This Site '"+sitesName+"' is present in excel at row no. --'"+m+"'.");
								break;
							}
						}
						
						if (!(found)) {
							msg="This Site '"+sitesName+"' is NOT present in any row in table of Sites tab.";
							fnpMymsg(msg);
							throw new Exception(msg);
							
						} 

				}
				*//**********************************************************************************************************************//*
				
				*//************Matching Pack Houses**********************************************************//*
				String packHouseArray[]= new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
				String packHouseName;
				for(int j=0; j <packHouseArray.length ;j++ ){	
					found=false;
					packHouseName = packHouseArray[j];
					 
					// fnpMymsg("PackHouse (in excel) at row no. '"+(j)+"' is--'"+sitesName+"'.");
						for(int m=1; m <= sitesRow ;m++ ){							
							temp = fnpFetchFromTable("SitesTab_Table_data", (m), nameColIndex_sitesTab);
							if(packHouseName.equalsIgnoreCase(temp)){
								found=true;
								fnpMymsg("This PackHouse '"+packHouseName+"' is present at sites tab in application at row no. --'"+m+"'.");
								break;
							}
						}
						
						if (!(found)) {
							msg="This PackHouse '"+packHouseName+"' is NOT present in any row in table of Sites tab.";
							fnpMymsg(msg);
							throw new Exception(msg);
							
						} 

				}
				
				
				int unitTypeColIndex_sitesTab = fnpFindColumnIndex("SitesTab_Table_HeaderRow", "Unit Type");
				int rowNoAtWhichQMSPresent=10;
				String unitTypeAt10thRow = fnpFetchFromTable("SitesTab_Table_data", rowNoAtWhichQMSPresent, unitTypeColIndex_sitesTab);
				if(unitTypeAt10thRow.contains("QMS")){
					fnpMymsg("This QMS '"+unitTypeAt10thRow+"' is present in last row i.e. "+rowNoAtWhichQMSPresent+" at sites tab in application.");
				}else{
					msg="This QMS '"+unitTypeAt10thRow+"' is NOT present in last row i.e. "+rowNoAtWhichQMSPresent+" at sites tab in application. Last row data in Unit Type column is --'"+unitTypeAt10thRow+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				*//************************************************************************************************//*
				
				
				
				
				
				
				*//**************Contacts data looks like*****************************************************************************//*
				//here we are just count no. of rows and count should be 1
				
				fnpClick_OR("iAg_ContactsTabLink");
				int contactsRow = fnpCountRowsInTable("ContactsTab_Table_data");
				
				fnpMymsg("Total Contacts rows in iPulse for current work order are ---"+contactsRow);
				if (contactsRow!=1){
					throw new Exception("Total count of rows in Contacts are '"+contactsRow+"' but they must be exact 1 rows .");
				}else{
					fnpMymsg("Total count of rows in Contacts are '"+contactsRow+"'.");
				}
				
				
				
				currentWorkingSheet="Producer Group";
				int managerFirstNameStartRowIndex=14;
				int managerFirstNameStartColIndex=9;
				 String managertmpExcelValue="";
				 String managertmpApplicationValue="";
				int firstNameColNo=fnpFindColumnIndex("ContactsTab_Table_HeaderRow", "First Name");
				managertmpExcelValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, managerFirstNameStartColIndex, (managerFirstNameStartRowIndex)).trim();
				managertmpApplicationValue=fnpFetchFromTable("ContactsTab_Table_data", 1, firstNameColNo);
				if(managertmpExcelValue.equalsIgnoreCase(managertmpApplicationValue)){
					fnpMymsg("Manager First Name is matched with excel i.e. in excel it is --'"+managertmpExcelValue+"' and in application it is --'"+managertmpApplicationValue+"'.");
				}else{
					fnpMymsg("Manager First Name is NOT matched with excel. Value in excel is this '"+managertmpExcelValue+"' but in application it is '"+managertmpApplicationValue+"'.");
					throw new Exception("Manager First Name is NOT matched with excel. Value in excel is this '"+managertmpExcelValue+"' but in application it is '"+managertmpApplicationValue+"'.");
				}

				
				int mobileStartRowIndex=19;
				int mobileStartColIndex=9;
				String mobileExcelValue="";
				 String mobileApplicationValue="";
				int mobileColNo=fnpFindColumnIndex("ContactsTab_Table_HeaderRow", "Cell/Mobile");
				mobileExcelValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, mobileStartColIndex, (mobileStartRowIndex)).trim();
				mobileApplicationValue=fnpFetchFromTable("ContactsTab_Table_data", 1, mobileColNo);
				if(mobileExcelValue.equalsIgnoreCase(mobileApplicationValue)){
					fnpMymsg("Cell/Mobile is matched with excel i.e. in excel it is --'"+mobileExcelValue+"' and in application it is --'"+mobileApplicationValue+"'.");
				}else{
					msg="Cell/Mobile NOT matched with excel. Value in excel is this '"+mobileExcelValue+"' but in application it is '"+mobileApplicationValue+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}

				
				
				*//************************************************************************************************//*
				
				
				
				
				
				
				
				
				
				
				
				*//**************Products & Coverage data looks like*****************************************************************************//*
				//here we are just count no. of rows and count should be 1
				
				fnpClick_OR("iAg_ProductsAndCoverageTabLink");
				int productCoverageRow = fnpCountRowsInTable("ProdcutsAndCoverageTab_Table_data");
				
				fnpMymsg("Total Products & Coverage tab rows in iPulse for current work order are ---"+productCoverageRow);
				if (productCoverageRow!=4){
					throw new Exception("Total count of rows in Products & Coverage tab are '"+productCoverageRow+"'  but they must be exact 4 rows .");
				}else{
					fnpMymsg("Total count of rows in Products & Coverage tab are '"+productCoverageRow+"'.");
				}
				
				

				
				
				
				
				
				fnpClick_OR("FirstProductExpandTriangleIcon");
				int firstInnerProductRowsAfterExpand = fnpCountRowsInTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data");
				
				fnpMymsg("Total Products & Coverage tab's first product inner rows in iPulse for current work order are ---"+firstInnerProductRowsAfterExpand);
				if (firstInnerProductRowsAfterExpand!=2){
					throw new Exception("Total count of rows in Products & Coverage's first product inner  table are '"+firstInnerProductRowsAfterExpand+"' but they must be exact 2 rows .");
				}else{
					fnpMymsg("Total count of rows in Products & Coverage's first product inner  table  are '"+firstInnerProductRowsAfterExpand+"'.");
				}
				
				
				
				
				int siteColNo=fnpFindColumnIndex("ProdcutsAndCoverageTab_FirstInnerProductTable_HeaderRow", "Site");
				String actualFirstSiteValue=fnpFetchFromTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data", 1, siteColNo);
				if(actualFirstSiteValue.equalsIgnoreCase(firstProducerName)){
					fnpMymsg("Actual first Site value is matched with excel first producer name i.e. in application it is --'"+actualFirstSiteValue+"' and in excel it is --'"+firstProducerName+"'.");
				}else{
					textMessage = "Actual first Site value is NOT matched with excel first producer name i.e. in application it is --'"+actualFirstSiteValue+"' and in excel it is --'"+firstProducerName+"'.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
					
				}

				
				
				
				
				
				fnpClick_OR("FirstViewSymbolforFirstProduct");
				if (fnpCheckElementDisplayed2("ViewProductLabelofDialog", 15)) {
					fnpMymsg("View product dialog box is opened successfully.");
				} else {
					fnpMymsg("Either View product dialog box is NOT opened successfully  or its label id is changed.");
				}
				
				
				
				
				
				String editProductLabel=fnpGetText_OR("ViewProductLabelofDialog");
				if (editProductLabel.equalsIgnoreCase("View Product")) {
					fnpMymsg("View product dialog box label's value is 'View Product'.");
				} else {
					fnpMymsg("View product dialog box label's value is NOT 'View Product'.");
				}
				
				*//************************************************************************************************//*
				
				
				
				
				fnpClick_OR("CloseSignOfViewProductLabelofDialog");
				
				
				fnpClick_OR("iAg_ServicesTabLink");
				
				int servicesRow = fnpCountRowsInTable("iAg_ServicesTab_Table_data");
				
				fnpMymsg("Total Services tab rows in iPulse for current work order are ---"+servicesRow);
				if (servicesRow!=1){
					throw new Exception("Total count of rows in Services tab are '"+servicesRow+"'  but they must be exact 1 row .");
				}else{
					fnpMymsg("Total count of rows in Services tab are '"+servicesRow+"'.");
				}
				*/
				
				fnpMatchScheme(IPULSE_CONSTANTS.Create_Membership_With_Option2_scheme_name);
				
				
			
		} catch (Throwable t) {

		fnpCommonFinalCatchBlock(t, "  Create  is fail . ", "Create ");

	}
		finally{
			//Collates the assertion results and marks test as pass or fail
			if (softAssert!=null) {
		
				fail = true;
				isTestPass = false;	
				softAssert.assertAll();
			}
			

		}

}
		
		
		
		

	@AfterTest
	public void reportTestResult() throws Throwable {
		if (driver!=null) {
			driver.quit();
		}
		
		fnpReportTestResult(hashXlData, currentSuiteName, currentSuiteXls, currentScriptCode, classNameText, isTestPass);

	}

}