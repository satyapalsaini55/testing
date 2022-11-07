package nsf.ecap.New_NSFOnline_Suite;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Sanity_testing extends TestSuiteBase_New_NSFOnline {
	
	
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	
	@Test( priority = 0)
	public void BS_01_Script_will_Continue_if_this_Step_Fail_______Verify_Login_Authentication_for_Incorrect_Password() throws Throwable{
		try{
			fnsApps_Report_Logs("############### Test Case ::0 BS_01_Script_will_Continue_if_this_Step_Fail_______Verify_Login_Authentication_for_Incorrect_Password ");
			if(env.equalsIgnoreCase("Test")){
				Verify_Login_Authentication_Done = true;
			}else{
				Verify_Login_Authentication_Done = false;
				throw new SkipException("Skipping Verification of Login Authentication as Envirnoment is not 'STAGING'.");
			}
				
			fnsVerify_Login_Authentication(Login_UserName);
		}catch(SkipException sk){
			throw new SkipException("Skipping Verification of Login Authentication as Envirnoment is not 'STAGING'.");
		}catch (Throwable t) {	
			throw new Exception(Throwables.getStackTraceAsString(t) );
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
