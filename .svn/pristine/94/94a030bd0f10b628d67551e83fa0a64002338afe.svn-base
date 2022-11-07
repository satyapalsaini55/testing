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

//BS-05
public class AH2_scheme extends TestSuiteBase_Agriculture_suite {
	
	
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
				
				fnpMatchScheme(IPULSE_CONSTANTS.AH2_scheme_name);
				
				
				


				
				
			
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