package nsf.ecap.util;

//import nsf.ecap.suiteA.NoSuchElementException;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.NoSuchElementException;

//import nsf.ecap.base.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestUtil  {

	
	// finds if the test suite is runnable 
		public static boolean isSuiteRunnable(Xls_Reader xls , String suiteName){
			boolean isExecutable=false;
			for(int i=2; i <= xls.getRowCount("Test Suite") ;i++ ){
				//String suite = xls.getCellData("Test Suite", "TSID", i);
				//String runmode = xls.getCellData("Test Suite", "Runmode", i);
			
				if(xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)){
					if(xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")){
						isExecutable=true;
					}else{
						isExecutable=false;
					}
				}

			}
			xls=null; // release memory
			return isExecutable;
			
		}
		
		
		public static String getBrowserName(Xls_Reader xls , String suiteName){
			String browserName=null;
			for(int i=2; i <= xls.getRowCount("Test Suite") ;i++ ){
				//String suite = xls.getCellData("Test Suite", "TSID", i);
				//String runmode = xls.getCellData("Test Suite", "Runmode", i);
			
				if(xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)){
					if(xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")){
						browserName=xls.getCellData("Test Suite", "Browser", i);
					}
				}

			}
			xls=null; // release memory
			return browserName;
			
		}
		
		
		
		public static String getExcelSiteURL(Xls_Reader xls , String suiteName){
			String url=null;
			for(int i=2; i <= xls.getRowCount("Test Suite") ;i++ ){
				//String suite = xls.getCellData("Test Suite", "TSID", i);
				//String runmode = xls.getCellData("Test Suite", "Runmode", i);
			
				if(xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)){
					if(xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")){
						url=xls.getCellData("Test Suite", "URL", i);
					}
				}

			}
			xls=null; // release memory
			return url;
			
		}
		
			
		
		
		
		
		
		
		// returns true if runmode of the test is equal to Y
		public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName){
			boolean isExecutable=false;
			for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
				//String tcid=xls.getCellData("Test Cases", "TCID", i);
				//String runmode=xls.getCellData("Test Cases", "Runmode", i);
				//System.out.println(tcid +" -- "+ runmode);
				
				
				if(xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)){
					if(xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")){
						isExecutable= true;
					}else{
						isExecutable= false;
					}
				}
			}
			
			return isExecutable;
			
		}
		
		
		// returns true if runmode of the test is equal to Y
		public static boolean isSubTestCaseRunnable(Xls_Reader xls, String testSheetName, String subTestCaseName){
			boolean isExecutable=false;
			for(int i=2; i<= xls.getRowCount(testSheetName) ; i++){
				//String tcid=xls.getCellData("Test Cases", "TCID", i);
				//String runmode=xls.getCellData("Test Cases", "Runmode", i);
				//System.out.println(tcid +" -- "+ runmode);
				
				
				if(xls.getCellData(testSheetName, "TCID", i).equalsIgnoreCase(subTestCaseName)){
					if(xls.getCellData(testSheetName, "Runmode", i).equalsIgnoreCase("Y")){
						isExecutable= true;
					}else{
						isExecutable= false;
					}
				}
			}
			
			return isExecutable;
			
		}
		
		
		
		// return the test data from a test in a 2 dim array
		public static Object[][] getData(Xls_Reader xls , String testCaseName){
			
			
			// if the sheet is not present
			if(! xls.isSheetExist(testCaseName)){
				xls=null;
				return new Object[1][0];
			}
			
			
			int rows=xls.getRowCount(testCaseName);
			int cols=xls.getColumnCount(testCaseName);
			//System.out.println("Rows are -- "+ rows);
			//System.out.println("Cols are -- "+ cols);
			
		    Object[][] data =new Object[rows-1][cols-3]; //working fine till 27-10-16 untill Runmode was 3rd last column no., means one column for later use was present
		   // Object[][] data =new Object[rows-1][cols-1]; // not working because if we use this we need to pass runmode, result ect. string variable in dataprovider method
			for(int rowNum=2;rowNum<=rows;rowNum++){
				for(int colNum=0;colNum<cols-3;colNum++){  //working fine till 27-10-16 untill Runmode was 3rd last column no., means one column for later use was present
				//for(int colNum=0;colNum<cols;colNum++){// not working because if we use this we need to pass runmode, result ect. string variable in dataprovider method
					//System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
					data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
					
				}
				//System.out.println();
			}
			return data;
			
		}
		
		// return the test data from a test in a 2 dim array
		public static Object[][] getDataInHash(Xls_Reader xls , String testCaseName){
	//		System.out.println("                         ");
		//	System.out.println("                         ");
	//		System.out.println("*************************************************");
			// if the sheet is not present
			if(! xls.isSheetExist(testCaseName)){
				xls=null;
				return new Object[1][0];
			}
			
			
			int rows=xls.getRowCount(testCaseName);
			int cols=xls.getColumnCount(testCaseName);
			
		    Object[][] data =new Object[rows-1][1];
		    
		    int r=0;
			for(int rowNum=2;rowNum<=rows;rowNum++){
				Hashtable <String,String>table=new Hashtable<String, String>();
				//HashMap <String,String>table=new HashMap<String, String>();
				
				//for(int colNum=0;colNum<cols-3;colNum++){ //working fine till 27-10-16 untill Runmode was 3rd last column no., means one column for later use was present
				for(int colNum=0;colNum<cols;colNum++){
			//		System.out.println("testcaseName -->"+testCaseName +"   ColNum --->"+colNum+"     RowNum--->"+rowNum);
			//		System.out.println(xls.getCellData(testCaseName, colNum, 1)+"--->"+xls.getCellData(testCaseName, colNum, rowNum));
					
				//	table.put(xls.getCellData(testCaseName, colNum, 1), xls.getCellData(testCaseName, colNum, rowNum));
					
					
					if (xls.getCellData(testCaseName, colNum, 1).trim().equalsIgnoreCase("")) {
						//nothing to do
					} else {
						table.put(xls.getCellData(testCaseName, colNum, 1), xls.getCellData(testCaseName, colNum, rowNum));
					}
					
				}
				data [r][0]=table;
				r++;
			}
			return data;
			
		}
		
		
		
		// checks RUnmode for dataSet
		public static String[] getDataSetRunmodes(Xls_Reader xlsFile,String sheetName){
			String[] runmodes=null;
			if(!xlsFile.isSheetExist(sheetName)){
				xlsFile=null;
				sheetName=null;
				runmodes = new String[1];
				runmodes[0]="Y";
				xlsFile=null;
				sheetName=null;
				return runmodes;
			}
			runmodes = new String[xlsFile.getRowCount(sheetName)-1];
			for(int i=2;i<=runmodes.length+1;i++){
				runmodes[i-2]=xlsFile.getCellData(sheetName, "Runmode", i);
			}
			xlsFile=null;
			sheetName=null;
			return runmodes;
			
		}

		// update results for a particular data set	
		public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum,String result){	
			xls.setCellData(testCaseName, "Results", rowNum, result);
		}
		
		// return the row num for a test
		public static int getRowNum(Xls_Reader xls, String id){
			for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
				String tcid=xls.getCellData("Test Cases", "TCID", i);
				
				if(tcid.equals(id)){
					xls=null;
					return i;
				}
				
				
			}
			
			return -1;
		}
		
		
/*		//check that Element is present or not
		public boolean isElementPresent(String id,WebDriver driver) {
			boolean present;
			try {
				driver.findElement(By.id(id));
				present = true;
			} catch (NoSuchElementException e) {
				present = false;
			}
			return present;
		}*/
		
		
		

		
		
		
		
		
		
}
