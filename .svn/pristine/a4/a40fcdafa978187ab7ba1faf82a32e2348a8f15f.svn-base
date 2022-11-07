/*package nsf.ecap.EPSF_suite;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Proposals_suite.Proposal_Won;
import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class EPSF_Draft_Req_Admin_Login extends TestSuiteBase_EPSF {
	 //BS-06



	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		EPSF_BS_01 = new EPSF_Draft_Req_Recvd();
		EPSF_BS_01.checkTestSkip(this.getClass().getSimpleName());

	}
	



	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "EPSF_Draft_Req_Admin_Login")
	public void Draft_tossed_returned_flow(
								Hashtable table) throws Throwable {

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to no " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to no " + (count + 1));
		}

		try {
			
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}


			hashXlData=new HashMap(table);

			fnpCommonCodeEPSFCreation(table);
			
			String EPSF_No=fnpGetText_OR("EPSF_No");
			
			fnpPFList("EPSFAuditorCollectedPFList", "EPSFAuditorCollectedPFListOptions", (String) table.get("Auditor_Collected_Flag"));	
			
			fnpCommonEPSFStatusChangedPFList( table, (String) table.get("Requested_ChangeStatus"));
			
			
			
			ArrayList epsfArrayList = fnpFetchEPSFFromDB(EPSF_No);
			int sizeArrayListEPSF=epsfArrayList.size();
			fnpMymsg("Pradeep size of list is ---"+sizeArrayListEPSF);			
			Assert.assertTrue(sizeArrayListEPSF==1, "Record is not entered in Oasis table in database");
			
			
			
			
			for (int i = 0; i < sizeArrayListEPSF; i++) {
				fnpMymsg("This is EPSF - " + i + "--->" + epsfArrayList.get(i));
				
			}
					
			
			
			
			
			fnpCommonClickEPSFIssuesTab();
			String sampleReviewRequiredFlag=((String) table.get("SampleReviewRequired")).trim();
			fnpPFList("SampleReviewRequiredPFList", "SampleReviewRequiredPFListOptions",sampleReviewRequiredFlag);
		
			fnpClick_OR("EPSF_SaveBtn");
			
			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after set flag "+(String) table.get("SampleReviewRequired")+
					"  in Issues Tab and click save button----" + fnpGetText_OR("TopMessage3"));
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be after set flag "+(String) table.get("SampleReviewRequired")+
					"  in Issues Tab and click save button.");

			
			
			fnpCommonClickEPSFInfoTab();
			fnpCommonEPSFStatusChangedPFList( table, (String) table.get("Received_ChangeStatus"));
			
			
			
			fnpCommonClickEPSFIssuesTab();
			fnpClick_OR("EPSF_AddIssueBtn");
			
			fnpPFList("EPSFIssueTypePFList", "EPSFIssueTypePFListOptions",(String) table.get("First_Issues_Type"));

			fnpGetORObjectX("EPSF_IssuesDescTxtBx_xpath").sendKeys((String)table.get("First_IssuesDescription"));
			//fnpClick_OR("EPSF_SaveNCloseDialogBtn");
			fnpClickInDialog_OR("EPSF_SaveNCloseDialogBtn");			
			int noOfRows=fnpCountRowsInTable("EPSF_IssuesTable");
			Assert.assertEquals(noOfRows, 1, "First Issue has not been added successfully.");
			fnpMymsg("First Issue has not been added successfully.");
			

			
			
			String current_epsfStatus=fnpGetText_OR("EPSF_Status");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("Hold_ChangeStatus")), "EPSF status not changed to --"+(String)table.get("Hold_ChangeStatus"));
			fnpMymsg("EPSF goes to '"+(String)table.get("Hold_ChangeStatus")+"' status.");
			
			
			
			fnpClick_OR("EPSF_AddIssueBtn");
			fnpPFList("EPSFIssueTypePFList", "EPSFIssueTypePFListOptions",(String) table.get("Second_Issues_Type"));
			fnpGetORObject("EPSF_IssuesDescTxtBx_xpath").sendKeys((String)table.get("Second_IssuesDescription"));						
			//fnpClick_OR("EPSF_SaveNCloseDialogBtn");
			fnpClickInDialog_OR("EPSF_SaveNCloseDialogBtn");			
			 noOfRows=fnpCountRowsInTable("EPSF_IssuesTable");
			Assert.assertEquals(noOfRows, 2, "2nd Issue has not been added successfully.");
			fnpMymsg("Second Issue has not been added successfully.");
			
			
			
			
			driver.close();
			String siteUrl;
		//	String siteUrl = CONFIG.getProperty("BranchSecureLoginURL");
			
			
			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("BranchSecureLoginURL");
				} else {
				//	siteUrl=excelSiteURL;
					String epsfLoginAsUrl = excelSiteURL.split("LoginAsTestSiteName:")[1];									
					siteUrl=epsfLoginAsUrl;

				}
			} else {
				siteUrl = CONFIG.getProperty("BranchSecureLoginURL");
			}
			

			
			
			
			
			
			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
			fnpLaunchBrowserAndLogin((String) table.get("LoginAsFieldServiceUser"), siteUrl, userName, password);
			
			fnpSearchEPSFOnly(EPSF_No);
			fnpCommonClickEPSFIssuesTab();
			
			
			
			int clearAuthorityColIndex=fnpFindColumnIndex("EPSF_IssuesTable_header", "Clearing Authority");
			//int rowNo=fnpFindRowContainsName("EPSF_IssuesTable", "EPSF_FLD_SRV", clearAuthorityColIndex);
			int rowNo=fnpFindRow("EPSF_IssuesTable", "EPSF_FLD_SRV", clearAuthorityColIndex);
			
			if (rowNo==2) {

				if (!(fnpCheckElementDisplayedImmediately("EPSFFirstIssueClearBtn"))) {
					fnpMymsg("Clear button is not present for issue type other than fld type in row 1 i.e. expected result");
					
				} else {
					throw new Exception ("Clear button is  Present for issue type other than fld type in row 1");
				}
			} else {
				if (rowNo==1) {
					if (!(fnpCheckElementDisplayedImmediately("EPSFSecondIssueClearBtn"))) {
						fnpMymsg("Clear button is not present for issue type other than fld type in row 2 i.e. expected result");
	
						
					} else {
						throw new Exception ("Clear button is  Present for issue type other than fld type in row 2");
					}

				}
			}
			
			
			fnpMymsg("Now going to clear fld type issue");
			if (rowNo==2) {
				fnpGetORObject("EPSF_ClearingCommentsSecondRowTxtArea_xpath").sendKeys((String)table.get("Second_ClearingComments"));
				fnpClick_OR("EPSFSecondIssueClearBtn");
				Assert.assertTrue(!(fnpCheckElementDisplayedImmediately("EPSFSecondIssueClearBtn")), 
						"After clearing fld type issue ,clear button for this should not be visible or present any more");
			} else {
				fnpGetORObject("EPSF_ClearingCommentsFirstRowTxtArea_xpath").sendKeys((String)table.get("First_ClearingComments"));
				fnpClick_OR("EPSFFirstIssueClearBtn");
				Assert.assertTrue(!(fnpCheckElementDisplayedImmediately("EPSFFirstIssueClearBtn")), 
						"After clearing fld type issue ,clear button for this should not be visible or present any more");
			}
			
			
			
			driver.close();
			

			fnpLaunchBrowserAndLogin((String) table.get("LoginAsSampleUser"), siteUrl, userName, password);
			
			fnpSearchEPSFOnly(EPSF_No);
			fnpCommonClickEPSFIssuesTab();
			
			
		//	int clearAuthorityColIndex=fnpFindColumnIndex("EPSF_IssuesTable_header", "Clearing Authority");
			//int smgRowNo=fnpFindRowContainsName("EPSF_IssuesTable", "SMG_ISSUE", clearAuthorityColIndex);
			int smgRowNo=fnpFindRow("EPSF_IssuesTable", "SMG_ISSUE", clearAuthorityColIndex);
			
			if (smgRowNo==2) {

				if (!(fnpCheckElementDisplayedImmediately("EPSFFirstIssueClearBtn"))) {
					fnpMymsg("Clear button is not present for issue type other than SMG_ISSUE type in row 1 i.e. expected result");
					
				} else {
					throw new Exception ("Clear button is  Present for issue type other than SMG_ISSUE type in row 1");
				}
			} else {
				if (smgRowNo==1) {
					if (!(fnpCheckElementDisplayedImmediately("EPSFSecondIssueClearBtn"))) {
						fnpMymsg("Clear button is not present for issue type other than SMG_ISSUE type in row 2 i.e. expected result");
	
						
					} else {
						throw new Exception ("Clear button is  Present for issue type other than SMG_ISSUE type in row 2");
					}

				}
			}
			
			
			fnpMymsg("Now going to clear SMG_ISSUE type issue");
			if (smgRowNo==2) {
				fnpGetORObject("EPSF_ClearingCommentsSecondRowTxtArea_xpath").sendKeys((String)table.get("Second_ClearingComments"));
				fnpClick_OR("EPSFSecondIssueClearBtn");
				Assert.assertTrue(!(fnpCheckElementDisplayedImmediately("EPSFSecondIssueClearBtn")), 
						"After clearing SMG_ISSUE type issue ,clear button for this should not be visible or present any more");
			} else {
				fnpGetORObject("EPSF_ClearingCommentsFirstRowTxtArea_xpath").sendKeys((String)table.get("First_ClearingComments"));
				fnpClick_OR("EPSFFirstIssueClearBtn");
				Assert.assertTrue(!(fnpCheckElementDisplayedImmediately("EPSFFirstIssueClearBtn")), 
						"After clearing SMG_ISSUE type issue ,clear button for this should not be visible or present any more");
			}
			
			
			
			 current_epsfStatus=fnpGetText_OR("EPSF_Status");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("HoldReleased_ChangeStatus")), "EPSF status not changed to --"+(String)table.get("HoldReleased_ChangeStatus"));
			fnpMymsg("EPSF goes to '"+(String)table.get("HoldReleased_ChangeStatus")+"' status.");
			
			
			 fnpCommonClickEPSFInfoTab() ;			
			 fnpCommonEPSFStatusChangedPFList( table, (String) table.get("Reviewed_ChangeStatus"));
			
				fnpClick_OR("EPSF_CrJobBtn");
				//	fnpWaitForVisible("TopMessage3");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				fnpMymsg("Top Message after clicking Create Job button----" + fnpGetText_OR("TopMessage3"));
				 SuccessfulMsg = fnpGetText_OR("TopMessage3");
				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, after clicking Create Job button");
				
				Thread.sleep(8000);
				fnpClick_OR("EPSF_AutoLoginToBeakerBtn");
				
				fnpClickAutoLoginToBeakerBtn();
				try{
					//	fnpWaitForVisible("TopMessage3");
					fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				}catch(Throwable t){				
					throw new Exception("Successful login message is not flashed after clicking 'Auto Login To Beaker' button");
				}
				fnpMymsg("Top Message after clicking Auto Login To Beaker button----" + fnpGetText_OR("TopMessage3"));
				 SuccessfulMsg = fnpGetText_OR("TopMessage3");
				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, after clicking Auto Login To Beaker");
					
				
				 current_epsfStatus = fnpGetText_OR("EPSF_Status");
				String current_epsfStatusListValue = fnpGetText_OR("EPSFStatusPFList");
				Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("LoggedIn_ChangeStatus")), "EPSF status not changed to '"+
						table.get("LoggedIn_ChangeStatus")+"'.");	
				Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(current_epsfStatusListValue), " EPSF status are not same " +
						"at both places i.e. at top and in status list");
				fnpMymsg("EPSF status  changed to '"+(String)table.get("LoggedIn_ChangeStatus")+"'.");
		
			
		} catch (Throwable t) {

			
			fnpCommonFinalCatchBlock( t,"  EPSF_Draft_Req_Admin_Login  method is failed . ","EPSF_Draft_Req_Admin_Login_flow") ;
			
			IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
			// browser and login again.
			driver.quit();
			
			

		}

	}







	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}







	@AfterTest
	public void reportTestResult() {
		int rowNum=currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel() );
		
		if (isTestPass){
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel() );
		}else{
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
		}
		
		

	}







	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		driver.quit();
		IsBrowserPresentAlready = false;
		killprocess();

	}




	
}

*/