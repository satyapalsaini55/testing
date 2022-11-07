package nsf.ecap.Audit_SAM_SPA_suite;


//ATA-72


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import test.Retry;

import com.google.common.base.Throwables;

//BS-01
public class SAM_assuring_all_screens_load extends TestSuiteBase_Audit_SAM_SPA_suite {

	int pcounter=0;
	SoftAssert softAssert = null ;
	
	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(@Optional String className) throws Throwable {

		try {

			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			//classNameText = className;
			setClassNameText( className);
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			//fnpMymsg("=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");

			if (!TestUtil.isTestCaseRunnable(Audit_SAM_SPA_suitexls, className)) {

				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				fnpMymsg("=========================================================================================================================================");
				throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");// reports
			}

			fnpMymsg("Going to execute the script for  '" + className + "'  as runmode set to Yes");// logs

			
			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			
			fail = false;
			isTestPass = true;

		}

		catch (SkipException sk) {
			skip = true;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(stackTrace);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}

	}

	
/*	@Test(retryAnalyzer=Retry.class)
	public void test() throws Throwable {
		pcounter++;
		fnpMymsg("run sequence--"+pcounter);
		//Assert.assertEquals("Satya", "Sat"+pcounter);
		Assert.assertEquals("Satya", "Satya");
	}
	
	*/
	
	

	
	
	
	
	//@Test(retryAnalyzer=Retry.class)
	@Test(priority = 1)
	//@Test(enabled = false)
	public void SAM_assuring_all_screens_load() throws Throwable {

		//fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Create");
		
		
		
		

		try {
			
			String textMessage;
			start = new Date();
			 
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}
			
			



			// Step =>Hover over the Menu option from the Home Page and select Search Audit link
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchAuditTopLink", "SAM_button");

			

			String originalHandle = driver.getWindowHandle();

			fnpMymsg(" Now going to Click SAM button.");
			ArrayList<String> listoldtabs2 = new ArrayList<String>(driver.getWindowHandles());
			fnpClick_OR("SAM_button");
			//fnpClick_OR("SAM_buttonForTESTING");
			
			

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			
			// wait till new tab is not opened successfully.
			int oldtabscount2 = listoldtabs2.size();
			int newTotal = tabs.size();
			int ii = 0;
			while (newTotal != (oldtabscount2 + 1)) {
				Thread.sleep(2000);
				tabs = new ArrayList<String>(driver.getWindowHandles());
				newTotal = tabs.size();
				ii = ii + 1;
				if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}

			}

			// Now switch to new tab
			int tabsCount = tabs.size();
			for (int i = 0; i < tabsCount; i++) {

				fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));
				if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
					// nothing to do

				} else {
					driver.switchTo().window(tabs.get(i));
					Thread.sleep(1000);
					break;
				}

			}
			
			driver.manage().window().maximize();
			Thread.sleep(1000);
			
			String url = driver.getCurrentUrl();
			
			if(env.equals("Test")) {
				assert url.contains("https://mytest.nsf.org/auditmgmt/sam"):"FAILED == Expected TEST Url is not opened, Url is ==>  (" + url + ")  ";
				fnpMymsg("Expected URL is opened ===> " + url );
			}else {
				assert url.contains("https://mystg.nsf.org/auditmgmt/sam"):"FAILED == Expected STAGE Url is not opened, Url is ==>  (" + url + ")  ";
				fnpMymsg("Expected URL is opened ===> " + url );
			}

			//fnsLoading_wait_In_SAM(2);
			//fnpLoading_wait_In_SAM_OLD();
			
			/*			if (!(fnpCheckElementDisplayed("Advanced_Filter_Options_ExpandBtn", 120))) {
			throw new Exception("Either SAM is not opened properly or Advanced_Filter_Options_ExpandBtn is not visible. See screenshot.");
							
		} 
	*/
			
			
			try{
				//fnsLoading_wait_In_SAM(1);
			//fnpWaitForVisible("Advanced_Filter_Options_ExpandBtn" ,120);//now bydefault it is coming expand from 28 Jan,2020
			//fnpWaitForVisible("Advanced_Filter_Options_CloseBtn" ,120);
			//fnpCheckElementDisplayed("Advanced_Filter_Options_ExpandBtn_OR_CloseBtn", 60);
			fnpWaitForVisible("Advanced_Filter_Options_Expandtriangle_header" ,40);
			
			
			}catch(Throwable t){
				throw new Exception("Either SAM is not opened properly or Advanced_Filter_Options_CloseBtn is not visible. See screenshot.");
			}
			
			
			//IPM-14869 and ESPA-200   .. This search table may or may not present so commenting this line.
/*			try{
			fnpLoading_wait_In_SAM_OLD();
			fnpWaitForVisible("SAMSearchTable" ,120);//IPM-14869 and ESPA-200   .. This search table may or may not present so commenting this line.
			}catch(Throwable t){
				throw new Exception("Either SAM is not opened properly or SAM default search table is not visible. See screenshot.");
			}
			
			*/
			
			textMessage = "SAM is  opened properly ";
			fnpMymsg(textMessage);
			//fnsLoading_wait_In_SAM(1);
			Thread.sleep(1000);
			fnpDisplayingMessageInFrame(textMessage, 10);
			
			
			//IPM-14869 and ESPA-200   .. This search table may or may not present so commenting this line.
/*			
			int totalRowsInDefaultSearch=fnpCountRowsInTable("SAMSearchTable");

			if (totalRowsInDefaultSearch<5) {
				textMessage = "SAM should have at least 5 visits but actually they are --'"+totalRowsInDefaultSearch+"', hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
				throw new Exception(textMessage);
				
				
			}else{

				textMessage = "SAM have default total visits visible on current screen are--"+totalRowsInDefaultSearch;
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
			}
			
			
			*/
			
			
			
			
			String expectedAuditType=(String) hashXlData.get("Audit_Type");
			
			//textMessage = "Now going to expand Advanced Search. Select audit type '"+expectedAuditType+"' and click on search button. ";//now bydefault it is coming expand from 28 Jan,2020
			textMessage = "Now selecting audit type '"+expectedAuditType+"' and click on search button. ";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			

			//fnpClick_OR_WithoutWait("Advanced_Filter_Options_ExpandBtn");//now bydefault it is coming expand from 28 Jan,2020
			if (fnpCheckElementDisplayedImmediately("Advanced_Filter_Options_ExpandBtn")) {
				fnpClick_OR_WithoutWait("Advanced_Filter_Options_ExpandBtn");
			} 
			
			fnpSelectValue_By_SelectClass(OR.getProperty("ProgramCode_dropDown"), "ISR");
			
			//fnsClick_OR("AuditorTypeTxtBxSAM");
			fnpClick_OR_WithoutWait("AuditorTypeTxtBxSAM");
			fnpType("OR", "AuditorTypeTxtBxSAM", expectedAuditType);

			//fnsLoading_wait_In_SAM(2);
			//Thread.sleep(5000);
			
			//String xpathForTypeList=".//div[@id='auditTypes-list']//ul/li[text()='"+expectedAuditType+"']";
			//".//li[text()='"+ expectedAuditType +"']/label";
			String xpathForTypeList1=".//*[text()='"+ expectedAuditType +"']";
			String xpathForTypeList2=".//li[text()='"+ expectedAuditType +"']/label/input";

			if (fnpCheckElementDisplayedImmediately_NOR(xpathForTypeList1)) {
				fnpClick_NOR_withoutWait(xpathForTypeList1);
				
			}else{
				Thread.sleep(1000);
				
			//one more time to check again the list is rendered or not after giving too much wait as well as loading too.
				
				if (fnpCheckElementDisplayedImmediately_NOR(xpathForTypeList2)) {
					fnpClick_NOR_withoutWait(xpathForTypeList2);
				}else{
					textMessage = " This Audit Type '"+expectedAuditType+"' is not present in drop down, hence failed.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
					}
			}
			
			
			if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome"))) {
				Thread.sleep(1000);
			}
			
			fnpClick_OR_WithoutWait("AuditorTypeLblSAM");
			
			//String selectedDropDownValue=".//*[@id='auditTypes_taglist']/li/span";
			String selectedDropDownValue=".//*[@class='k-reset']/li/span[1]";
			String selectedValue=fnpGetText_NOR(selectedDropDownValue);
			if (!(selectedValue.equalsIgnoreCase(expectedAuditType))) {
				fnpMymsg("Audit Type down value has not been selected properly. Selected value is --"+selectedValue);
				throw new Exception("Audit Type down value has not been selected properly. " +
						"Selected value is --'"+selectedValue+"'. See screenshot.");
				
			} else {
				fnpMymsg("Audit Type down value has  been selected properly. Selected value is --"+selectedValue);
			}
			
			
			fnaSelectingCriteriaInAdvancedFilterInSAM();
			
			fnpClick_InSAM_OR("FilterBtnSAM");

			textMessage = " The search result should have '"+expectedAuditType+"'  in the Audit Type column. " +
					"It could have Other audits along with '"+expectedAuditType+"'  as comma separated values.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			
			
			
			
			int totalVisisbleRows=0;
			String auditTypeValueXpath="";
			String auditTypeValue;
			int generalCounter=0;
			
			
			try{
				fnpWaitForVisible("FooterPagination_xpath");
			}catch(Throwable t1){
				msg="Pagination is not visible/present, so may be no records present on  clicking search button after selecting filter criteria.";
				fnpMymsg(msg);
				throw new Exception(msg +"/n/n/n"+t1.getMessage());
			}
			
			
			
			
			String paginationValue=fnpGetText_OR("FooterPagination_xpath");
			String totalRecord=paginationValue.split("of")[1].trim();
			String totalRecordCount=totalRecord.split("items")[0].trim();
			int lastRecordRowNo=Integer.parseInt(totalRecordCount);
			
			int auditTypeColNo=fnaFindColumnIndex_NOR_TraversingFromVariousNodes_SAM(OR.getProperty("SAMSearchTable_header"), "Audit Type(s)",2);
		//	while (fnpCheckElementDisplayedImmediately("NextBtnSAM")) {
			
			
			while (true) {	
/*			int a=2;
			int b=5;
			while (a>b) {	*/

/*				
				if (generalCounter==0) {
					
				   softAssert = new SoftAssert();
				   //Assertion failing
				   softAssert.fail("once failed intentionally by Pradeep");
					
					break;						
				}
				*/
				
				
				
				totalVisisbleRows=fnpCountRowsInTable("SAMSearchTable");
				
				String postalCodeXpath;
				for (int i = 1; i < totalVisisbleRows+1; i++) {
					generalCounter++;
					//auditTypeValueXpath=".//*[@id='visitGrid']/table/tbody/tr["+i+"]/td["+auditTypeColNo+"]/div/div";
					auditTypeValueXpath=".//*[@role='presentation']/table/tbody/tr["+i+"]/td["+auditTypeColNo+"]";
					auditTypeValue=fnpGetText_NOR(auditTypeValueXpath);
					try{
						fnpMove_To_Element_and_DoubleClick_NOR(auditTypeValueXpath);
					}catch(Throwable t){
						//nothing to do for now...
					}
					
					
					
					if( (auditTypeValue.length()>10) && (!(auditTypeValue.contains(expectedAuditType))) ){
						try{
							fnpMove_To_Element_and_DoubleClick_NOR(auditTypeValueXpath);
						}catch(Throwable t){
							//nothing to do for now...
						}
						Thread.sleep(1500);
						auditTypeValueXpath="//div[@class='k-tooltip-content']";
						auditTypeValue=fnpGetText_NOR(auditTypeValueXpath);
					}
					
					
/*					
					//double click is not getting performed on Audit type (not even manually), so going to double click on its adjacent col i.e. on postal code
					postalCodeXpath=".//*[@id='visitGrid']/table/tbody/tr["+i+"]/td["+(auditTypeColNo-1)+"]/span";
					fnpMove_To_Element_and_DoubleClick_NOR(postalCodeXpath);
					Thread.sleep(2000);
					
					*/
					
					
					if (!(auditTypeValue.contains(expectedAuditType))) {
					//if (!(auditTypeValue.equalsIgnoreCase(expectedAuditType))) {
						
						
						textMessage = "Audit Type in row no. --'"+i+"' and col no. -'"+auditTypeColNo+"' is not matched as " +
								"expected is --'"+expectedAuditType+"' and actual is --'"+auditTypeValue+"', hence failed.";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
						throw new Exception(textMessage);
						
						
						
					} else {
						fnpMymsg("Audit Type in row no. --'"+generalCounter+"' and col no. -'"+auditTypeColNo+"' is  matched as " +
								"expected is --'"+expectedAuditType+"' and actual is --'"+auditTypeValue+"'.");

					}
					
					
					
				}
				
				
				if (fnpCheckElementDisplayedImmediately("NextBtnSAM")) {
					if (fnpCheckElementDisplayedImmediately("NextBtnSAM_disable")) {
						break;
					}
					//fnpScrollToElement(fnpGetORObjectX("NextBtnSAM"));
					fnpClick_OR_WithoutWait("NextBtnSAM");	
					//Thread.sleep(1000);
					//Thread.sleep(200);

					
				}else {
					
				
					
					//if ((generalCounter+1)!=lastRecordRowNo) {
					if ((generalCounter)!=lastRecordRowNo) {
						
						
						/*						
						textMessage = " This row no. -'"+(generalCounter+1)+"' is not present as Next button is " +
								"disable but in pagination it shows total records are --"+lastRecordRowNo;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 10);
						
						
						//throw new Exception(textMessage);
						//break;
						
						
						
						
						
						 //Creating softAssert object
						    softAssert = new SoftAssert();
						   //Assertion failing
						   softAssert.fail(textMessage);
						   fnpDesireScreenshot((generalCounter+1)+"RowNotPresent");
						   break;
						   
					*/

     
						   if ((generalCounter)<lastRecordRowNo) {
								
								textMessage = " This row no. -'"+(generalCounter+1)+"' is not present as Next button is " +
										"disable but in pagination it shows total records are --"+lastRecordRowNo;
								fnpMymsg(textMessage);
								fnpDisplayingMessageInFrame(textMessage, 5);
								
								 //Creating softAssert object
								    softAssert = new SoftAssert();
								   //Assertion failing
								   softAssert.fail(textMessage);
								   fnpDesireScreenshot((generalCounter+1)+"RowNotPresent");
								   break;
						} else {
							textMessage = " Total rows are more in table than the total rows shown in pagination. In pagination total rows are --"+lastRecordRowNo+" but actually they are --"+generalCounter;
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							
							 //Creating softAssert object
							    softAssert = new SoftAssert();
							   //Assertion failing
							   softAssert.fail(textMessage);
							   //fnpDesireScreenshot((generalCounter+1)+"RowsPresentInTableButInPaginationRowsAreLess");
							   fnpDesireScreenshot((generalCounter)+"RowsPresentInTableButInPaginationRowsAreLess");
							   break;
						}
				   
						
					} else {
						break;
					}
					
					
					//break;
					
					
				}

				
				
			}
			
			
			
			
/*			
			while(true){
				if (fnpCheckElementDisplayedImmediately("PreviousBtnSAM")) {
					fnpClick_OR_WithoutWait("PreviousBtnSAM");
					fnpDisplayingMessageInFrame("Now clicking 'Previous' button to go back to the initial first record.", 10);
					fnpLoading_wait_In_SAM_JustCheckLoadingIcon_NotWaitForIt();
				}else
					break;
			}
			
			
			*/
			
			
			fnpGoingBackToFirstRecordInSAMWithoutClickingPreviousBtn();
			
			
			
			
			
			
/*			
			
			int visitNoColNo=fnpFindColumnIndex_NOR_TraversingFromVariousNodes_SAM(OR.getProperty("SAMSearchTable_header"), "Visit No.",2);
			String firstVisitXpath=".//*[@id='visitGrid']/table/tbody/tr[1]/td["+visitNoColNo+"]/a";			
			String firstVisitNo=fnpGetText_NOR(firstVisitXpath);

			
			textMessage = " Click on top search result to view visit details. " +
					" 1. The Visit no should match the visit no from the previous page.  "+
						" 2. Facility code can not be null.   "+
						" 3. Audit type should have at least one value.";

			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			
			
			fnpClick_NOR_withoutWait(firstVisitXpath);
			fnpLoading_wait_In_SAM_OLD();
			
			*/
			
			

			
			String firstVisitNo=fnpClickFirstVisitWhichDoesNotHaveAuditor();
			
			
			
			
			
			String visitNoAtVisitDetailPage=fnpGetText_OR("VisitNoAtVisitDetailPage_xpath").trim();
			fnpMymsg(" visit no. at detail page  is ---"+visitNoAtVisitDetailPage);
			if (!(visitNoAtVisitDetailPage.equalsIgnoreCase(firstVisitNo))) {
				
				textMessage = " First visit no. '"+firstVisitNo+"' in search table which is clicked " +
						"is not matching with the visit no. at detail page '"+visitNoAtVisitDetailPage+"', hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
				
			} else {
				fnpMymsg("First visit no. '"+firstVisitNo+"' in search table which is clicked is  matched successfully with the visit no. at detail page '"+visitNoAtVisitDetailPage+"'.");
			}
			
			
			
			String facilityCodeAtVisitDetailPage=fnpGetText_OR("FacilityCodeAtVisitDetailPage_xpath");
			fnpMymsg(" Facility Code at detail page  is ---"+facilityCodeAtVisitDetailPage);
			if (facilityCodeAtVisitDetailPage.equalsIgnoreCase("")) {
				textMessage = " Facility Code should not be blank at visit detail page as its value is --'"+facilityCodeAtVisitDetailPage+"', hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
				
			} else {
				//nothing for now
			}
			
			
			
			
			
			String auditTypeValueAtVisitDetailPage=fnpGetText_OR("AuditTypeDropDownValueAtVisitDetailPage_xpath");
			
			int totalSelectedValuePresentInAuditTypeDropDown=fnpFindNoOfElementsWithThisXpath("AuditTypeDropDownValueAtVisitDetailPage_xpath");
			if (!(totalSelectedValuePresentInAuditTypeDropDown>0)) {
				
				textMessage = " Audit type should have at least one value but it is coming blank, hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
			} else {
				fnpMymsg("Audit type should have at least one value and current value is --"+totalSelectedValuePresentInAuditTypeDropDown);
			}
					
			
			
			textMessage = " Going to click on the 'Monthly Assign' tab and there should be at least one auditor listed.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			
			if (!(fnpCheckElementDisplayedImmediately("MonthlyAuditorGrid"))) {
				fnpClick_InSAM_OR("MonthlyAssignTabLink");
			}
			
			
			
			
			
			
			WebElement MonthlyAssignTabLinkElement = fnpGetORObjectX("MonthlyAssignTabLink");
			MonthlyAssignTabLinkElement.sendKeys(Keys.ARROW_DOWN);
			MonthlyAssignTabLinkElement.sendKeys(Keys.ARROW_DOWN);
			
			
			
			
			
			
			int totalAuditor=fnpFindNoOfElementsWithThisXpath("AllAuditorAtMonthlyAuditorGridXpath");
			List<WebElement> auditorList = fnpGetORObject_list("AllAuditorAtMonthlyAuditorGridXpath", 0, 1);
					
			
			
			if (!(totalAuditor>0)) {
				
				textMessage = " There should be at least one auditor listed  at Monthly Assign tab but it is coming blank.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
				
			} else {
				fnpMymsg("Total auditor present at  Monthly Assign tab tab  are --"+totalAuditor);
				String firstAuditor = fnpReturnText_notCallItDirectly(auditorList.get(0));				
				fnpMymsg(" First auditor value is ---"+firstAuditor);
			}
					
	
			textMessage = " Going to click on tab 'Daily Schedule' and there should be at least one auditor listed.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);

			fnpClick_InSAM_OR("DailyScheduleTabLink");
			//Thread.sleep(20000);
			//fnpLoading_wait_In_SAM_OLD();
			
			
			
			WebElement dailyScheduleTabLinkElement = fnpGetORObjectX("DailyScheduleTabLink");
			dailyScheduleTabLinkElement.sendKeys(Keys.ARROW_DOWN);
			dailyScheduleTabLinkElement.sendKeys(Keys.ARROW_DOWN);
			
			
			totalAuditor=fnpFindNoOfElementsWithThisXpath("AllAuditorAtDailyAuditorGridXpath");
			auditorList = fnpGetORObject_list("AllAuditorAtDailyAuditorGridXpath", 0, 1);
			if (!(totalAuditor>0)) {
				textMessage = " There should be at least one auditor listed at Daily Schedule tab but it is coming blank, hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
				
			} else {
				fnpMymsg("Total auditor present  at Daily Schedule tab  are --"+totalAuditor);
				String firstAuditor = fnpReturnText_notCallItDirectly(auditorList.get(0));				
				fnpMymsg(" First auditor value is ---"+firstAuditor);
			}
				

			
			textMessage = " Going to click on Offer link next to the auditor's name, offers popup should open up. " +
					"	In the popup, Facility code should not be empty, Audit type should not be empty.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			
			
			

			
			
			Actions action = new Actions(driver);
			auditorList = fnpGetORObject_list("AllAuditorAtDailyAuditorGridXpath", 0, 1);
			WebElement firstAuditorElement = auditorList.get(0);
			action.moveToElement(firstAuditorElement).build().perform();
			
			// below is logic to move to auditor in first row to next row , so that page is visible on screen to max records assign to this variable whileMaxRow
			int whileRowcounter=0;
			int whileMaxRow=3;
			WebElement auditorElement ;
			String auditorDistanceElementXpath ;
			while(auditorList.size() >whileRowcounter){
				
				if (whileRowcounter< whileMaxRow) {
/*					
					action = new Actions(driver);
					auditorElement = auditorList.get(whileRowcounter);
					action.moveToElement(auditorElement).build().perform();
					
					*/
					
					//auditorDistanceElementXpath=".//*[@id='dailyAuditorGrid']/div[2]/table/tbody/tr["+(whileRowcounter+1)+"]/td[2]";
					auditorDistanceElementXpath=".//*[@role='presentation']/div/div//table/tbody/tr["+(whileRowcounter+1)+"]/td[2]";
						
					if (fnpCheckElementPresenceImmediately_NotInOR(auditorDistanceElementXpath) ){
						action = new Actions(driver);
						action.moveToElement(driver.findElement(By.xpath(auditorDistanceElementXpath))).doubleClick().build().perform();
						Thread.sleep(500);
					}
					
					
				} else {
					break;
				}
				
				
				whileRowcounter++;
				
			}
					
			
			List<WebElement> list2 = driver.findElements(By.xpath(OR.getProperty("FirstOfferLinkNextToAuditor")));
			
		//	List<WebElement> list3 = driver.findElements(By.xpath(OR.getProperty("OffersLinkNextToAuditor1")));
		//	List<WebElement> list4 = driver.findElements(By.xpath(OR.getProperty("OffersLinkNextToAuditor2")));
			
			if (list2.size()<1) {
				textMessage = " Offer link is not visible next to the first auditor’s name, hence failed.";
				//textMessage = " Not a single Offer link is  visible next to the  auditor's name, hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
			} else {
				//nothnig for now
			}
			

			list2.get(0).click();
			Thread.sleep(1000);

			 String facilityCodeAtOfferPagePage = fnpGetText_OR("FacilityCodeAtOfferPopup_xpath");
			fnpMymsg(" Facility Code at offer popup  is ---"+facilityCodeAtOfferPagePage);
			if (facilityCodeAtOfferPagePage.equalsIgnoreCase("")) {

				textMessage = "Facility Code should not be blank at Offer popup as its value is --'"+facilityCodeAtOfferPagePage+"', hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);

			} else {
				//nothing for now
			}

			 String auditTypeAtOfferPagePage = fnpGetText_OR("AuditTypeDropDownValueAtOfferPopup_xpath");
			fnpMymsg("Audit Type at offer popup  is ---"+auditTypeAtOfferPagePage);
			if (auditTypeAtOfferPagePage.equalsIgnoreCase("")) {
				textMessage = "Audit Type  should not be blank at Offer popup as its value is --'"+auditTypeAtOfferPagePage+"', hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				
				
			} else {
				//nothing for now
			}
			
		
			textMessage = "Going to close the popup.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpClick_InSAM_OR("OfferCloseLink");
			
			
			textMessage = "Going to click 'Assign in Map' tab link.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpClick_InSAM_OR("AssignInMapTabLink");
			
			/*			
			int a=driver.findElements(By.xpath(".//img[contains(@src,'/map-icon')]")).size();
			System.out.println("Total a elements are:--"+a);
			int b=driver.findElements(By.xpath(".//marker[contains(@icon,'/map-icon')]")).size();
			System.out.println("Total b elements are:--"+b);
			
			int c=driver.findElements(By.xpath(".//div[@class='gmnoprint']/img[contains(@src,'/map-icon2.png')]")).size();
			System.out.println("Total a elements are:--"+c);
			
			
			*/
			
			
			//fnsLoading_wait_In_SAM(2);
			
			//int totalABlurbImageAtAssignInMapTab=fnpFindNoOfElementsWithThisXpath("A_blurb_image_at_AssignInMap_Tab");
			int typeA_ABlurbImageAtAssignInMapTab=fnpFindNoOfElementsWithThisXpath("TypeA_A_blurb_image_at_AssignInMap_Tab");
			int typeB_ABlurbImageAtAssignInMapTab=fnpFindNoOfElementsWithThisXpath("TypeB_A_blurb_image_at_AssignInMap_Tab");
			int totalABlurbImageAtAssignInMapTab=typeA_ABlurbImageAtAssignInMapTab+typeB_ABlurbImageAtAssignInMapTab;
			
			if (totalABlurbImageAtAssignInMapTab<1) {
				textMessage = "Total 'A' blurb image at this Assign in Map tab are ---'"+totalABlurbImageAtAssignInMapTab+"', hence failed because at least one 'A' blurb should appear";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
				throw new Exception(textMessage);
			} else {
				textMessage = "Total 'A' blurb image at this Assign in Map tab are ---'"+totalABlurbImageAtAssignInMapTab+"', hence passed this step because at least one 'A' blurb should appear";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
			}
			
			

/*			textMessage = "Going to click on back button on the top right corner and the SAM home page should be loaded .";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			*/
			
			fnpClick_InSAM_OR("BackButtonSAM");
			
			
			//fnpWaitForVisible("FilterBtnSAM");
			//fnpWaitForVisible("Advanced_Filter_Options_ExpandBtn");//now bydefault it is coming expand from 28 Jan,2020
			fnpWaitForVisible("Advanced_Filter_Options_ExpandBtn_OR_CloseBtn");
			
			textMessage = "On clicking 'Back' button, SAM home page is loaded successfully.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			
			
			
			//Thread.sleep(100);
			isTestPass = true;
			fnpMymsg(" **************************************************************");
			
			
			
			  //Collates the assertion results and marks test as pass or fail
			//   softAssert.assertAll();
			

		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  SAM_assuring_all_screens_load is fail . ", "SAM_assuring_all_screens_load");
			
			

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
		
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();
		
		

		

	}

}