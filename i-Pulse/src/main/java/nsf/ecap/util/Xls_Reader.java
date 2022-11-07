package nsf.ecap.util;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;


import java.io.*;
import java.util.Calendar;
import java.util.Date;


public class Xls_Reader {
	public static String filename = System.getProperty("user.dir")+"\\src\\config\\testcases\\TestData.xlsx";
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;
	
	public Xls_Reader(String path) {
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
	// returns the data from a cell
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			try{// putting this in try catch block b/c sometime when using this in data provider if a column is blank between then it throws exception
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
			}catch(Exception e){
				//nothing to do
			}
			

			
		}
		
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		//System.out.println(cell.getCellType());
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		           //System.out.println(cellText);

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	// returns the data from a cell
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";
		
	
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		  return cell.getStringCellValue();
	  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	          // System.out.println(cellText);

	         }

		  
		  
		  return cellText;
	  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
	
	
	
	
	
	// returns the data from a cell
	public String getCellData2(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";
		
	
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType()==Cell.CELL_TYPE_STRING  || cell.getCellType()==Cell.CELL_TYPE_FORMULA)
		  return cell.getStringCellValue();
	  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC  ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	          // System.out.println(cellText);

	         }

		  
		  
		  return cellText;
	  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
	
	
	
	
	
	// returns the data from a cell--Currently using this in iAg for option 2
	/*************Taking indexes as a parameter for both row and col (not row no and col no.)****/
	public String getCellData_withoutCheckingAnyCellType(String sheetName,int colIndex,int rowIndex){
		try{	
			
/*			
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			XSSFSheet sheet = workbook.getSheetAt(index);
			fis.close();
			
			*/
			
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			XSSFRow  row = sheet.getRow(rowIndex);
			XSSFCell cell = row.getCell(colIndex);
			

			return cell.getStringCellValue();
			}
		catch(Exception e){
			
			e.printStackTrace();
			return "row index '"+rowIndex+"' or column index ' "+colIndex +"' does not exist  in xls";
		}
	}
	
	
	
	
	
	
	
	
	
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		
/*		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
	*/	
		
		for(int i=0;i<row.getLastCellNum();i++){
			try{// puttin this in try catch block b/c sometime when using this in data provider if a column is blank between then it throws exception
			
				//System.out.println(row.getCell(i).getStringCellValue());
				
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				colNum=i;
			}catch(Exception e){
				//nothing to do
			}			
		}
	
		
		
		
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    // cell style
	    //CellStyle cs = workbook.createCellStyle();
	    //cs.setWrapText(true);
	    //cell.setCellStyle(cs);
	
	    cell.setCellValue(data);
	    
	    
	    if(data.equalsIgnoreCase("Pass")){
	      cell.setCellType(Cell.CELL_TYPE_STRING);
          CellStyle style = workbook.createCellStyle();
        //  style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
          style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
          style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
          cell.setCellStyle(style); 
	    
	    }
	    if(data.equalsIgnoreCase("Fail")){
		      cell.setCellType(Cell.CELL_TYPE_STRING);
	          CellStyle style = workbook.createCellStyle();
	       //   style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	          style.setFillForegroundColor(IndexedColors.RED.getIndex());
	          style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
	          cell.setCellStyle(style); 
		    
		    }
	    
	    if(data.equalsIgnoreCase("Skip")){
		      cell.setCellType(Cell.CELL_TYPE_STRING);
	          CellStyle style = workbook.createCellStyle();
	       //   style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	          style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
	          style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
	          cell.setCellStyle(style); 
		    
		    }
	    
	    
	    

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,int colNo,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		
/*		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
	*/	

	
		colNum=colNo;
		
		
		if(colNum==-1)
			return false;

		//sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    // cell style
	    //CellStyle cs = workbook.createCellStyle();
	    //cs.setWrapText(true);
	    //cell.setCellStyle(cs);
		
		//cell.setCellType(cell.CELL_TYPE_STRING);
		int p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(data);
	    
	  

	    
	    
	    fileOut = new FileOutputStream(path);
	  //  fileOut = new FileOutputStream("D:\\TEST_full_scripts\\i-Pulse\\docs\\pradeepTest\\a.xlsm");

		//workbook.write(fileOut);
		workbook.write(fileOut);
		
	

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
		//System.out.println("setCellData setCellData******************");
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		//System.out.println("A");
		row=sheet.getRow(0);
		
		
/*		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colNum=i;
		}
		
		*/
		
		for(int i=0;i<row.getLastCellNum();i++){
			try{// puttin this in try catch block b/c sometime when using this in data provider if a column is blank between then it throws exception
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colNum=i;
			}catch(Exception e){
				//nothing to do
			}			
		}
	
		
		
		
		if(colNum==-1)
			return false;
		sheet.autoSizeColumn(colNum); //ashish
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);
			
	    cell.setCellValue(data);
	    XSSFCreationHelper createHelper = workbook.getCreationHelper();

	    //cell style for hyperlinks
	    //by default hypelrinks are blue and underlined
	    CellStyle hlink_style = workbook.createCellStyle();
	    XSSFFont hlink_font = workbook.createFont();
	    hlink_font.setUnderline(XSSFFont.U_SINGLE);
	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
	    hlink_style.setFont(hlink_font);
	    //hlink_style.setWrapText(true);

	    XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
	    link.setAddress(url);
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);
	      
	    fileOut = new FileOutputStream(path);
		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	// returns true if sheet is created successfully else false
	public boolean addSheet(String  sheetname){		
		
		FileOutputStream fileOut;
		try {
			 workbook.createSheet(sheetname);	
			 fileOut = new FileOutputStream(path);
			 workbook.write(fileOut);
		     fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// returns true if sheet is removed successfully else false if sheet does not exist
	public boolean removeSheet(String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return false;
		
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// returns true if column is created successfully
	public boolean addColumn(String sheetName,String colName){
		//System.out.println("**************addColumn*********************");
		
		try{				
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return false;
			
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		sheet=workbook.getSheetAt(index);
		
		row = sheet.getRow(0);
		if (row == null)
			row = sheet.createRow(0);
		
		//cell = row.getCell();	
		//if (cell == null)
		//System.out.println(row.getLastCellNum());
		if(row.getLastCellNum() == -1)
			cell = row.createCell(0);
		else
			cell = row.createCell(row.getLastCellNum());
	        
	        cell.setCellValue(colName);
	        cell.setCellStyle(style);
	        
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}
	// removes a column and all the contents
	public boolean removeColumn(String sheetName, int colNum) {
		try{
		if(!isSheetExist(sheetName))
			return false;
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		style.setFillPattern(HSSFCellStyle.NO_FILL);
		
	    
	
		for(int i =0;i<getRowCount(sheetName);i++){
			row=sheet.getRow(i);	
			if(row!=null){
				cell=row.getCell(colNum);
				if(cell!=null){
					cell.setCellStyle(style);
					row.removeCell(cell);
				}
			}
		}
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
	    fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
  // find whether sheets exists	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
	
	// returns number of columns in a sheet	
	public int getColumnCount(String sheetName){
		// check if sheet exists
		if(!isSheetExist(sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
		
		
		
	}
	
	
	public int getColumnCount(String sheetName,int rowNo_basisOfWhichItCanCountNoOfCols){
		// check if sheet exists
		if(!isSheetExist(sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo_basisOfWhichItCanCountNoOfCols-1);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
		
		
		
	}
	
	
	//String sheetName, String testCaseName,String keyword ,String URL,String message
	public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){
		//System.out.println("ADDING addHyperLink******************");
		
		url=url.replace('\\', '/');
		if(!isSheetExist(sheetName))
			 return false;
		
	    sheet = workbook.getSheet(sheetName);
	    
	    for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
	    		//System.out.println("**caught "+(i+index));
	    		setCellData(sheetName, screenShotColName, i+index, message,url);
	    		break;
	    	}
	    }


		return true; 
	}
	public int getCellRowNum(String sheetName,String colName,String cellValue){
		
		for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
		return -1;
		
	}
	

	
		
	// to run this on stand alone
	public static void main(String arg[]) throws IOException{
		
		//System.out.println(filename);
		Xls_Reader datatable = null;
		

			 datatable = new Xls_Reader("D:\\pradeep\\Controller.xlsx");
				for(int col=0 ;col< datatable.getColumnCount("TC5"); col++){
					System.out.println(datatable.getCellData("TC5", col, 1));
				}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// returns true if data is set successfully else false
	public boolean fillRowColor_old(String sheetName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		//int colNum=-1;
		int colNum=5;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

	//	row=sheet.getRow(rowNum);

		
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);		
			cell.setCellValue(data);
	    
	      cell.setCellType(Cell.CELL_TYPE_STRING);
          CellStyle style = workbook.createCellStyle();
        //  style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
          style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
          style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
          cell.setCellStyle(style); 
	    
    

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	

	// returns true if data is set successfully else false
	public boolean fillRowColor(String sheetName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		//int colNum=-1;
		//int colNum=5;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

	//	row=sheet.getRow(rowNum);

		
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		
		for(int pp=0;pp<10;pp++){
			cell = row.getCell(pp);	
			if (cell == null)
		        cell = row.createCell(pp);		
				cell.setCellValue(data);
		    
		      cell.setCellType(Cell.CELL_TYPE_STRING);
	          CellStyle style = workbook.createCellStyle();
	        //  style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	        //  style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
	          style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
	        // style.setFillForegroundColor(IndexedColors..getIndex());
	          style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
	          cell.setCellStyle(style); 
		    
			
			
		}

    

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	

	
	
	// returns true if data is set successfully else false
	public void setCellDataForiAgOption2Sheet(String pathToCopy2,String givenTimeStamp) throws Throwable{
		

		try{
		fis = new FileInputStream(path); 
		
		workbook = new XSSFWorkbook(fis);


		String sheetName = "Producer Group";
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		//if(index==-1)
			//return false;
		
		
		sheet = workbook.getSheetAt(index);		

		row=sheet.getRow(0);
		

		
		
		//Business Name
		colNum=3;
		int rowNum = 13;
		//String businessName = option2Excel.getCellData2(currentSheet, 3, 13);
		row=sheet.getRow(rowNum-1);
		String businessName = row.getCell(colNum).getStringCellValue().trim();
		System.out.println("Current Business Name--"+businessName);
		String data = businessName+givenTimeStamp;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		int p=cell.getCellType();
		cell.setCellType(p);
	  //  cell.setCellValue(data);
	    cell.setCellValue(data+"\n");
	   
	    
	    
	    
	    
	    //VAT No
	    colNum=9; //after minus (1)
	    rowNum = 11;
		row=sheet.getRow(rowNum-1);
		String vatNo = row.getCell(colNum).getStringCellValue().trim();
		System.out.println("vatNo--"+vatNo);
		data = vatNo+givenTimeStamp;
	    workbook=fnpForInsertingDataInOption2Excel( workbook, sheetName, colNum, rowNum, data);
		    
		   
	    
	    
	    
	    
	    
	    
	   // Address Line 1
	    colNum=3;
	    rowNum = 14;
	    row = sheet.getRow(rowNum-1);
		String addLine1 = row.getCell(colNum).getStringCellValue().trim();
		String dataaddress = addLine1+givenTimeStamp;
	    //workbook=fnpForInsertingDataInOption2Excel( workbook, sheetName, colNum, rowNum, dataaddress);
	    workbook=fnpForInsertingDataInOption2Excel( workbook, sheetName, colNum, rowNum, dataaddress+"\n");

	
	
	   // Post code
		colNum=3;
		rowNum = 18;
		row=sheet.getRow(rowNum-1);
		String postCode = row.getCell(colNum).getStringCellValue().trim();
		System.out.println("Current Post code is--"+postCode);
		String modifiedTmpStmp=givenTimeStamp.replaceAll("-", "");
		modifiedTmpStmp=modifiedTmpStmp.replaceAll(":", "");
		modifiedTmpStmp=modifiedTmpStmp.replaceAll(" ", "");
		modifiedTmpStmp=modifiedTmpStmp.replaceAll("AM", "");
		modifiedTmpStmp=modifiedTmpStmp.replaceAll("PM", "");
		//String modifiedPostCode = addLine1+givenTimeStamp;
		//modifiedTmpStmp=modifiedTmpStmp.substring(0, 11);
		modifiedTmpStmp=modifiedTmpStmp.substring(2, 11);//picking character from 2 till 11 b/c postcode can take max. 12 characters, and in script we are appending 1 or 2 digit, so keeping this of 9 digit before


		row = sheet.getRow(rowNum-1);
		cell = row.getCell(colNum);	

		 p=cell.getCellType();
		cell.setCellType(p);
		 cell.setAsActiveCell();
	    //cell.setCellValue(modifiedTmpStmp);
	    cell.setCellValue(modifiedTmpStmp+"\n");
	    
	    
	    
	    
		    
	    
	    //QMS Site Detail Bussiness Name
	    colNum=3;
	    rowNum = 26;
	    row = sheet.getRow(rowNum-1);
		businessName = row.getCell(colNum).getStringCellValue().trim();	
		data = businessName+givenTimeStamp;
	    workbook=fnpForInsertingDataInOption2Excel( workbook, sheetName, colNum, rowNum, data);
		    
	    
	    
	    //QMS Site Detail Address Line 1
	    colNum=3;
	    rowNum = 27;
	    row = sheet.getRow(rowNum-1);
		addLine1 = row.getCell(colNum).getStringCellValue().trim();
		dataaddress = addLine1+givenTimeStamp;
	    workbook=fnpForInsertingDataInOption2Excel( workbook, sheetName, colNum, rowNum, dataaddress);
		    
		    
	    
	    //QMS Site Detail Post Code
	    colNum=3;
	    rowNum = 31;
	    row = sheet.getRow(rowNum-1);
	    workbook=fnpForInsertingDataInOption2Excel( workbook, sheetName, colNum, rowNum, modifiedTmpStmp);
		    
		   
	    
	    
		    
		    /********************************************/
		    //Producers sheet
			 sheetName = "Producers";
			 index = workbook.getSheetIndex(sheetName);
			 colNum=-1;
			 
/*			if(index==-1)
				return false;
			*/
			
			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);
			
			//producer Name
			colNum=4;
			rowNum = 15;
			

			while(true){
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);	
				String producerName = row.getCell(colNum).getStringCellValue().trim();
				if (producerName.trim().equalsIgnoreCase("")) {
					break;
				} else {
					producerName=producerName+givenTimeStamp;
					System.out.println("New producer name is--"+producerName);
				}
				
				 p=cell.getCellType();
				 cell.setCellType(p);
				 cell.setAsActiveCell();
				 cell.setCellValue(producerName+"\n");
				 
				 rowNum=rowNum+1;
				 Thread.sleep(1000); 
				
			}
		   
		   
		    
		    
		    
		    
		    
			//Address Line 1
			colNum=5;
			rowNum = 15;
			

			while(true){
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);	
				String addLine1_producerSheet = row.getCell(colNum).getStringCellValue().trim();
				if (addLine1_producerSheet.trim().equalsIgnoreCase("")) {
					break;
				} else {
					addLine1_producerSheet=addLine1_producerSheet+givenTimeStamp;
					System.out.println("New producer name is--"+addLine1_producerSheet);
				}
				
				 p=cell.getCellType();
				 cell.setCellType(p);
				 cell.setAsActiveCell();
				 cell.setCellValue(addLine1_producerSheet+"\n");
				 
				 rowNum=rowNum+1;
				 Thread.sleep(1000); 
				
			}
		   
		   
		    
			//Post Code
			colNum=9;
			rowNum = 15;
			
			int i=1;
			while(true){
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);	
				String postCode_producerSheet = row.getCell(colNum).getStringCellValue().trim();
				if (postCode_producerSheet.trim().equalsIgnoreCase("")) {
					break;
				} else {
					postCode_producerSheet=modifiedTmpStmp+i;
					System.out.println("New postCode is--"+postCode_producerSheet);
				}
				
				 p=cell.getCellType();
				 cell.setCellType(p);
				 cell.setAsActiveCell();
				 cell.setCellValue(postCode_producerSheet+"\n");
				 
				 rowNum=rowNum+1;
				 i++;
				 Thread.sleep(1000); 
				
			}
		    
			 /********************************************/ 
		    
		    
		    
		    
		    
		    
			
		    /****************Sites sheet****************************/
		    //Sites sheet
			 sheetName = "Sites";
			 index = workbook.getSheetIndex(sheetName);
			 colNum=-1;
			 
			 
/*			if(index==-1)
				return false;
			*/
			
			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);
			
			//producer Name
			colNum=6;
			rowNum = 15;
			

			while(true){
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);	
				String producerName = row.getCell(colNum).getStringCellValue().trim();
				if (producerName.trim().equalsIgnoreCase("")) {
					break;
				} else {
					producerName=producerName+givenTimeStamp;
					System.out.println("New producer name is--"+producerName);
				}
				
				 p=cell.getCellType();
				 cell.setCellType(p);
				 cell.setAsActiveCell();
				 cell.setCellValue(producerName+"\n");
				 
				 rowNum=rowNum+1;
				 Thread.sleep(1000); 
				
			}
		   
		    
		    
			//Address Line 1
			colNum=7;
			rowNum = 15;
			

			while(true){
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);	
				String addLine1_producerSheet = row.getCell(colNum).getStringCellValue().trim();
				if (addLine1_producerSheet.trim().equalsIgnoreCase("")) {
					break;
				} else {
					addLine1_producerSheet=addLine1_producerSheet+givenTimeStamp;
					System.out.println("New producer name is--"+addLine1_producerSheet);
				}
				
				 p=cell.getCellType();
				 cell.setCellType(p);
				 cell.setAsActiveCell();
				 cell.setCellValue(addLine1_producerSheet+"\n");
				 
				 rowNum=rowNum+1;
				 Thread.sleep(1000); 
				
			}
		   
		   
		    
			//Post Code
			colNum=11;
			rowNum = 15;
			
			 i=1;
			while(true){
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);	
				String postCode_producerSheet = row.getCell(colNum).getStringCellValue().trim();
				if (postCode_producerSheet.trim().equalsIgnoreCase("")) {
					break;
				} else {
					postCode_producerSheet=modifiedTmpStmp+i;
					System.out.println("New postCode is--"+postCode_producerSheet);
				}
				
				 p=cell.getCellType();
				 cell.setCellType(p);
				 cell.setAsActiveCell();
				 cell.setCellValue(postCode_producerSheet+"\n");
				 
				 rowNum=rowNum+1;
				 i++;
				 Thread.sleep(1000); 
				
			}
		    
			 /********************************************/ 
			
			
			
			
			

			
			
			
			
		    
		    
	    
	    
	   // fileOut = new FileOutputStream(path);
	    
	    String outputFileName = pathToCopy2;
	    fileOut = new FileOutputStream(outputFileName);
		workbook.write(fileOut);
	    fileOut.close();	

		}
		catch(Exception e){
			throw new Exception(e);
			//return false;
		}
		//return true;
	}
	
	
	
	
	// returns true if data is set successfully else false
	//NOT USING IT
	public boolean setCellDataForiAg_Certification_Autotest(String pathToCopy2,String givenTimeStamp,String CMI,String ggn,String site,long auditNo,String todayDate){
		

		try{
		fis = new FileInputStream(path); 
		
		workbook = new XSSFWorkbook(fis);


		String sheetName = "Client";
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);		

		row=sheet.getRow(0);
		

		
		
		//CMi
		colNum=2;
		int rowNum = 10;
	
		row=sheet.getRow(rowNum-1);
		//String currentcmi = row.getCell(colNum).getStringCellValue().trim();
		//System.out.println("Current Cmi-"+currentcmi);
		String data = CMI;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		int p=cell.getCellType();
		cell.setCellType(p);
		
	    cell.setCellValue(data);
	    //cell.setCellValue(data+"\n");
	   
	    
	    
		
		//GGn
		colNum=2;
		 rowNum = 11;
	
		row=sheet.getRow(rowNum-1);
		// String currentggn = row.getCell(colNum).getStringCellValue().trim();
		//System.out.println("Current ggn-"+currentggn);
		 data = ggn;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		 p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(data);
	    //cell.setCellValue(data+"\n");
	    
	    
	    
		
		//Site
		colNum=5;
		 rowNum = 39;
	
		row=sheet.getRow(rowNum-1);
		//String currentSite = row.getCell(colNum).getStringCellValue().trim();
		//System.out.println("Current Site-"+currentSite);
		 data = site;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		 p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(data);
	    //cell.setCellValue(data+"\n");
	    
	    
	    
	    
	    
	    
		
		//Audit
		colNum=9;
		 rowNum = 39;
	
		row=sheet.getRow(rowNum-1);
		//String currentAudit = row.getCell(colNum).getStringCellValue().trim();
		//System.out.println("Current Audit-"+currentAudit);
		// long data1 = auditNo;
		 String data1 = String.valueOf(auditNo);


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		 p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(data1);
	   // cell.setCellValue(data1+"\n");
	    
	    
	    
	    
	    
		//Date  - in 40th row
		colNum=3;
		 rowNum = 40;
	
		row=sheet.getRow(rowNum-1);
		 String date_Data = todayDate;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		 p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(date_Data);
	    
	    
	    
	    
		//Date  - in 48th row
		colNum=9;
		 rowNum = 48;
	
		row=sheet.getRow(rowNum-1);
		  date_Data = todayDate;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		 p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(date_Data);
	    
	    
	    
		//Date  - in 49th row
		colNum=9;
		 rowNum = 49;
	
		row=sheet.getRow(rowNum-1);
		  date_Data = todayDate;


		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		 p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(date_Data);
	    
	    
	    
	    
	    
	   

	 /********************************************/ 
		    
		    
		    
	
			
			
			
			
		    
		    
	    
	    
	   // fileOut = new FileOutputStream(path);
	    
	    String outputFileName = pathToCopy2;
	    fileOut = new FileOutputStream(outputFileName);
		workbook.write(fileOut);
	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static XSSFWorkbook fnpForInsertingDataInOption2Excel(XSSFWorkbook workbook,String sheetName,int colIndex,int rowNo,String data){

		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;

		
		
		XSSFSheet sheet = workbook.getSheetAt(index);

		XSSFRow row=sheet.getRow(0);

		
		
		
		
		//Field
		colNum=colIndex;
		int rowNum = rowNo;
		//String businessName = option2Excel.getCellData2(currentSheet, 3, 13);
		row=sheet.getRow(rowNum-1);



		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		XSSFCell cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);


		int p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(data);
	    
	    return workbook;
	   
	    
	}
	
	
	
	// returns true if data is set successfully else false
	public boolean setCellData_OnlyForInsertingDataIniAgToUpload(String sheetName,int colNo,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		
/*		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
	*/	

	
		colNum=colNo;
		
		
		if(colNum==-1)
			return false;

		//sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    // cell style
	    //CellStyle cs = workbook.createCellStyle();
	    //cs.setWrapText(true);
	    //cell.setCellStyle(cs);
		
		//cell.setCellType(cell.CELL_TYPE_STRING);
		int p=cell.getCellType();
		cell.setCellType(p);
	    cell.setCellValue(data);
	    
	  

	    
	    
	    fileOut = new FileOutputStream(path);
	  //  fileOut = new FileOutputStream("D:\\TEST_full_scripts\\i-Pulse\\docs\\pradeepTest\\a.xlsm");

		//workbook.write(fileOut);
		workbook.write(fileOut);
		
	

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}
