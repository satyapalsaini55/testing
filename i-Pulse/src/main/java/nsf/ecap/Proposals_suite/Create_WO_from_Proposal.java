package nsf.ecap.Proposals_suite;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Create_WO_from_Proposal extends TestSuiteBase_Proposal {

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		count = -1;
		BS_P01 = new Proposal_Won();
		BS_P01.checkTestSkip(this.getClass().getSimpleName());

	}







//	@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "Create_WO_from_ProposalDataProvider")
	public void Create_WO_from_Proposal_flow(Hashtable <String,String>table) throws Throwable {
		// test the runmode of current dataset

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test  data set  no. " + (count + 1)+"is -- N");
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test  data set  no. " + (count + 1)+"is -- N");
		}else{
			skip = false;
		}


		
		
		try {
			
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}

			
			fnpCommonProposalCodePart2TillClickEditQuestLink_updated(table);
			
			fnpCommonProcessQuestionnairesSet(table.get("QuestionNAnswerSets"));
			
			
			
			fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled( table);
			
			
			
			fnpClick_OR("Prop_ProposalDetailsTab");

			fnpWaitForVisible("Prop_StatusList");
			fnpPFList("Prop_StatusList", "Prop_StatusListOptions", table.get("Change_status"));
	
			fnpClick_OR("Prop_SaveBtn");
			

			fnpCheckErrMsg("Error is thrown by application after changing status to 'WON' in Status list  ");

			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status to '"+table.get("Change_status")+"' in Status list  ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be status has not been changed/updated to 'LOST'successfully.");

			String status = fnpGetORObjectX("Prop_StatusList").getText();
			Assert.assertTrue(status.equalsIgnoreCase(table.get("Change_status")), "Proposal Status is not become to '"+table.get("Change_status")+"' .");
			fnpMymsg("Proposal status has become to '"+table.get("Change_status")+"' now.");
			
			//fetching value of cert decider, primary contact , proposal id from info tab
			fnpClick_OR("Prop_ClientInfoTab");
			 String certDecider_inProposal = fnpGetText_OR("Prop_CertDecider_PFList");
			 String primaryContact_inProposal = fnpGetText_OR("Prop_WOPrimaryContact_PFList");
			 String proposal_id_inProposal = fnpGetText_OR("Prop_id_AtInfoTab");
			
			//fetching value of scope from proposal text tab
			 fnpClick_OR("Prop_ProposalTextTab");
			 String scopeValue_inProposal = fnpGetText_OR("Prop_ScopeTxtBx");
			
			 
			
			 fnpClick_OR("Prop_ClientInfoTab");
			
			
			fnpClick_OR("Prop_GenerateWorkOrderBtn");
			
			
			///fnpClick_OR("Prop_GenerateWorkOrderYesConfirmationBtn");
			
			
			
			
			String workOrderNo_text = fnpGetText_OR("NewlyCreatedWorkOrderNo");
			workOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg("Work Order created successfully.");
			fnpMymsg("Newly created WO no. is:" + workOrderNo);

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			fnpWaitForVisible("TopBannerWOStatus");
			String NewlyWOStatus = fnpGetText_OR("TopBannerWOStatus");
			Assert.assertEquals(NewlyWOStatus, "DRAFT", "Newly created WO status is not 'DRAFT'.The WO should get created in DRAFT status. ");
			fnpMymsg("Newly created WO status is 'DRAFT'");
			
			
			String NewlyWOType = fnpGetText_OR("NewlyCreatedWorkOrderType");
			Assert.assertEquals(NewlyWOType, "MOD_BRACK", "Newly created WO type is not 'MOD_BRACK'.The WO type should be MOD_BRACK. ");
			fnpMymsg("Newly created WO type is 'MOD_BRACK'. ");
			
			
			//fetching value of cert decider, primary contact , proposal id from info tab of work order
			fnpClick_OR("InfoTab_EditWO");
			 String certDecider_inWO = fnpGetText_OR("InfoTab_CertDeciderPFList");
			 String primaryContact_inWO   = fnpGetText_OR("InfoTab_PrimaryContactPFList");
			 String proposal_id_inWO  = fnpGetText_OR("ProposalIdAtWOInfoTab");
			
			//fetching value of scope 
			 String scopeValue_inWO  = fnpGetText_OR("WOScope");
			
			
			
			
			 String txt;
			 //compare all fetched values from proposal with wo
			 if (certDecider_inProposal.equalsIgnoreCase(certDecider_inWO)) {
				 txt="CertDecider '"+certDecider_inProposal+"' in Proposal is matched with CertDecider '"+certDecider_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame_fnpMymsg(txt, 4);
				 Thread.sleep(2000);
			} else {
				 txt="CertDecider '"+certDecider_inProposal+"' in Proposal is NOT matched with CertDecider '"+certDecider_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame(txt, 4);
				 Thread.sleep(1000);
			}
			 
			 
			 
			 
			 
			 
			 if (primaryContact_inProposal.equalsIgnoreCase(primaryContact_inWO)) {
				 txt="Primary Contact '"+primaryContact_inProposal+"' in Proposal is matched with Primary Contact '"+primaryContact_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame_fnpMymsg(txt, 4);
				 Thread.sleep(2000);
			} else {
				 txt="Primary Contact '"+primaryContact_inProposal+"' in Proposal is NOT matched with Primary Contact '"+primaryContact_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame(txt, 4);
				 Thread.sleep(1000);
			}
			 
			
			 
			 
			 
			 
			 if (proposal_id_inProposal.equalsIgnoreCase(proposal_id_inWO)) {
				 txt="Proposal Id '"+proposal_id_inProposal+"' in Proposal is matched with Proposal Id  '"+proposal_id_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame_fnpMymsg(txt, 4);
				 Thread.sleep(2000);
			} else {
				 txt="Proposal Id  '"+proposal_id_inProposal+"' in Proposal is NOT matched with Proposal Id  '"+proposal_id_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame(txt, 4);
				 Thread.sleep(1000);
			}
			 
			 
			 
			 
			 if (scopeValue_inProposal.equalsIgnoreCase(scopeValue_inWO)) {
				 txt="Scope '"+scopeValue_inProposal+"' in Proposal is matched with Scope  '"+scopeValue_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame_fnpMymsg(txt, 4);
				 Thread.sleep(2000);
			} else {
				 txt="Scope  '"+scopeValue_inProposal+"' in Proposal is NOT matched with Scope  '"+scopeValue_inWO+"' in WO";
				 fnpMymsg(txt);
				 fnpDisplayingMessageInFrame(txt, 4);
				 Thread.sleep(1000);
			}
			 
			 
			 
			 
			 
			 
			

			fnpMymsg("****************************************************************");

		} catch (Throwable t) {
				
				fnpCommonFinalCatchBlock(t, "  Create_WO_from_Proposal_flow  is fail . ", "Create_WO_from_Proposal_flowFail");
				IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
				// browser and login again.
			//	driver.quit();
			
			}

	}







	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		
		if (skip)
			TestUtil.reportDataSetResult(Proposals_suitexls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(Proposals_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(Proposals_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}







	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum=Proposals_suitexls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		Proposals_suitexls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel() );
		
		if (isTestPass){
			TestUtil.reportDataSetResult(Proposals_suitexls, "Test Cases", TestUtil.getRowNum(Proposals_suitexls, this.getClass().getSimpleName()), "PASS");
			Proposals_suitexls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel() );
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		}else{
			TestUtil.reportDataSetResult(Proposals_suitexls, "Test Cases", TestUtil.getRowNum(Proposals_suitexls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		

		
		fnpCommonCloseBrowsersAndKillProcess();
		
	}







	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
	//	driver.quit();
		//IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();

		killprocess();

	}






/*
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}
*/	
	
/*	@DataProvider
	public Object[][] getTestData() {
		//return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
		return TestUtil.getDataInHash(Proposals_suitexls, this.getClass().getSimpleName());
	}*/

}
