package nsf.ecap.sustainability_Suite;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.google.common.base.Throwables;
public class Extends_Method_of_WO extends TestSuiteBase {
	
	/**** To find the Row no in a table ****/
	public static int fngFindRowContainsName_NOR_ForAI(String tableDataXpath, String containsName, int colNo) throws Throwable { // have to modify this function.

		int resultRowNo = 0;
		int retries = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = tableDataXpath + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					
					
					if (Value.toLowerCase().contains(containsName.toLowerCase())) {
						resultRowNo = i;
						break;
					}
					
					
				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for " + containsName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for " + containsName);
					continue;
				} else {
					throw is;
				}

			} catch (Throwable t) {
				if (retries < 4) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					return resultRowNo;
					/*
					 * String msg = t.getMessage(); throw new Exception(msg);
					 */
				}

			}
		}
	}
	public static int fngFindRowContainsName_NOR(String tableDataXpath, String containsName, int colNo) throws Throwable { // have to modify this function.

		int resultRowNo = 0;
		int retries = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = tableDataXpath + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					
					String xpathExpression1 = null;
					String billCodeValue = null;	
					xpathExpression1 = xpathRow + "[" + i + "]/td[" + 1 + "]";//1 for bill code column here
					billCodeValue = driver.findElement(By.xpath(xpathExpression1)).getText().trim();
					if(CONFIG.getProperty("Environment").equalsIgnoreCase("Staging")){
						if (Value.toLowerCase().contains(containsName.toLowerCase()) && billCodeValue.toLowerCase().contains("0200-AUDIT".toLowerCase())) {//0171-AUDIT
							resultRowNo = i;
							break;
						}
					}else if(CONFIG.getProperty("Environment").equalsIgnoreCase("Test")){
						if (Value.toLowerCase().contains(containsName.toLowerCase()) && billCodeValue.toLowerCase().contains("0200-AUDIT".toLowerCase())) {//0171-RDYREV
							resultRowNo = i;
							break;
						}
					}
					
					
				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for " + containsName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for " + containsName);
					continue;
				} else {
					throw is;
				}

			} catch (Throwable t) {
				if (retries < 4) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {

					return resultRowNo;

					/*
					 * String msg = t.getMessage(); throw new Exception(msg);
					 */
				}

			}
		}
	}
	
	
	
	public static void fnaTake_Screen_Shot(String ImageName) throws Exception {
		String MessageAfterFormat=fnaRemoveFormatting_for_FileName(ImageName);
		try {
			SShots = SShots + 1;
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			fnpMymsg("error screenshot name is---" + ImageName);
			String updatedImageName = ImageName + "--"+ SShots + ".png";
		//	FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//")));
			
			
			File screenshot_folder = new File((System.getProperty("user.dir") + screenshots_path + "//screenshots_"+ currentSuiteCode + "//" + currentScriptCode ));
			if (!screenshot_folder.exists()) {
				FileUtils.forceMkdir(screenshot_folder);
			}
			
			
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			ImageIO.write(image, "png", new File(
					(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//" + updatedImageName)));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
	public static String fnaRemoveFormatting_for_FileName(String s) {

		s = s.replace("\\", " ");
		s = s.replace("/", " ");
		s = s.replace(":", " ");
		s = s.replace("*", " ");
		s = s.replace("?", " ");
		s = s.replace("<", " ");
		s = s.replace(">", " ");
		s = s.replace("|", " ");
		s = s.trim();

		return s;
	}
	
	
	
	//Function for Screen date format 
	public static String fnaScreenShot_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}
	
	
	
	
	
	
	
	
	
	

	/*********** wait till element get visible ************/
	public static void fngSustWaitForVisible(String XpathKey) throws Throwable {


		fnpIpulseDuringLoading();

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitForVisible for element ---" + XpathKey + " ---for chance --" + whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.NoSuchElementException.class);

				if (OR_SUS.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
					case "id":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
						break;

					case "name":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
						break;

					case "linkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
						break;

					case "partialLinkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
						break;

					case "tagName":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
						break;

					case "className":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
						break;

					case "cssSelector":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
						break;

					case "xpath":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					default:
						/****** By default Xpath will be assumed *****/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitForVisible for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath")) | (t.getMessage().contains("Timed out after"))) {
						throw new Exception(" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

		}

	}
	/**** To find the Row no in a table ****/
	public static int fngFindRowContainsName_NOR_CertAudit(String tableDataXpath, String containsName, int colNo) throws Throwable { // have to modify this function.

		int resultRowNo = 0;
		int retries = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = tableDataXpath + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					
					String xpathExpression1 = null;
					String Value1 = null;	
					xpathExpression1 = xpathRow + "[" + i + "]/td[" + 1 + "]";//1 for bill code column here
					Value1 = driver.findElement(By.xpath(xpathExpression1)).getText().trim();
					if(CONFIG.getProperty("Environment").equalsIgnoreCase("Staging")){
						if (Value.toLowerCase().contains(containsName.toLowerCase()) && Value1.toLowerCase().contains("0200-AUDIT".toLowerCase())) {//0171-AUDIT 
							resultRowNo = i;
							break;
						}
					}else if(CONFIG.getProperty("Environment").equalsIgnoreCase("Test")){
						if (Value.toLowerCase().contains(containsName.toLowerCase()) && Value1.toLowerCase().contains("0200-AUDIT".toLowerCase())) {//0171-CERTAUD
							resultRowNo = i;
							break;
						}
					}
				}
				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}
			}
			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for " + containsName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for " + containsName);
					continue;
				} else {
					throw is;
				}

			} catch (Throwable t) {
				if (retries < 4) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {

					return resultRowNo;

					/*
					 * String msg = t.getMessage(); throw new Exception(msg);
					 */
				}

			}
		}
	}
	/**** Function clicking of an object (button etc) which is present in OR_SUS ****/
	public static void fngSustClick_OR(String xpathkey) throws Throwable {

		int i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		
		fnpCheckError("");
		
		fngSustClick_OR_SUST_WithoutWait(xpathkey);
		fnpLoading_wait();
		fnpIpulseDuringLoading();

	}
	/****
	 * Function clicking of an object (button etc) which is present in OR
	 * without handling loading Wait
	 ****/
	public static void fngSustClick_OR_SUST_WithoutWait(String xpathkey) throws Throwable {
		int retries = 0;

		int i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		fnpCheckError("");
		fngWaitForVisible(xpathkey);

		fngWaitTillVisiblityOfElementNClickable_OR(xpathkey);

		while (true) {
			try {
				fngGetORObjectX(xpathkey).click();
				fnpMymsg(" Clicked '" + xpathkey + "' .");
				break;

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpClick_OR function for " + xpathkey);
					// continue;
				} else {
					throw e;
				}
			}

			catch (org.openqa.selenium.WebDriverException w) {

				if ((w.getMessage().contains("not clickable at point")) | (w.getMessage().contains("unknown error:"))) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpWorkAroundToClickbottomFooter();
						Thread.sleep(1000);
						fnpMymsg(retries + "In WebDriverException catch block of fnpClick_OR function for " + xpathkey);
						// continue;
					} else {
						throw w;
					}
				} else {
					throw w;
				}
			}

		}

		fnpCheckError("");

	}
	/******* Insert value in text box ***/
	public static void fngType(String ORName, String xpath, String value) throws Throwable {

		WebElement wb = null;

		int iwhileCounter = 0;
		while (true) {
			iwhileCounter++;
			if (ORName == "OR_SUS") {
				wb = fngGetORObjectX(xpath);
				// wb.sendKeys("");
				try {
					wb.sendKeys("");
					wb.clear();
					Thread.sleep(1000);

					fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value);
					// Thread.sleep(500);
					wb = fngGetORObjectX(xpath);
					wb.sendKeys(value);
				} catch (org.openqa.selenium.InvalidElementStateException ie) {
					/****
					 * we are using this fnpType function of a drop down in
					 * Oasis where inserting time, so in those cases clear will
					 * not work..
					 */
					if (iwhileCounter > 5) {
						throw ie;
					}

				} catch (Throwable t) {
					if (iwhileCounter > 5) {
						throw t;
					}

				}
				wb = fngGetORObjectX(xpath);
			} else {

				if (ORName == "" | ORName == null) {
					// wb=driver.findElement(By.xpath(xpath));
					wb = fnpGetORObjectX___NOR(xpath);
					// wb.sendKeys("");
					try {
						wb.sendKeys("");
						wb.clear();
						Thread.sleep(1000);
						fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value);
						// Thread.sleep(500);
						wb = fnpGetORObjectX___NOR(xpath);
						wb.sendKeys(value);
					} catch (org.openqa.selenium.InvalidElementStateException ie) {
						/****
						 * we are using this fnpType function of a drop down in
						 * Oasis where inserting time, so in those cases clear
						 * will not work..
						 */

						if (iwhileCounter > 5) {
							throw ie;
						}

					} catch (Throwable t) {
						if (iwhileCounter > 5) {
							throw t;
						}
					}

					wb = fnpGetORObjectX___NOR(xpath);
				} else {
					// for later use for new OR
				}
			}

			String insertedText = "";

			if (ORName == "OR_SUS") {

				insertedText = fngGetAttribute_OR(xpath, "value");
				if (insertedText.equalsIgnoreCase(value)) {
					fnpMymsg("@  -OR_SUS--inserted text is matched . Expected is '" + value + "' and actual is--'" + insertedText + "'.");
					break;
				} else {
					fnpMymsg("@  -OR_SUS--inserted text is NOT matched . Expected is '" + value + "' and actual is--'" + insertedText + "'.");

				}

			} else {

				if (ORName == "" | ORName == null) {

					insertedText = fnpGetORObjectX___NOR(xpath).getAttribute("value").trim();
					if (insertedText.equalsIgnoreCase(value)) {
						fnpMymsg("@  -NOR--inserted text is matched . Expected is '" + value + "' and actual is--'" + insertedText + "'.");
						break;
					} else {
						fnpMymsg("@  -NOR--inserted text is Not  matched . Expected is '" + value + "' and actual is--'" + insertedText + "'.");
						wb.clear();
					}
				}
			}

			if (iwhileCounter > 5) {
				fnpMymsg("@  - after trying 5 times in inserting text by function fnpType, now exiting the while loop.");
				break;

			}
		}

	}
	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR_SUS
	 * 
	 * @throws Throwable
	 *************/
	/*public static WebElement fngGetORObjectX(String XpathKey) throws Throwable {

		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

		int retries = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class).ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class);

				if (OR_SUS.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
					case "id":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
						return driver.findElement(By.id(locatorValue));
					

					case "name":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));// id
						wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
						return driver.findElement(By.name(locatorValue));
					

					case "linkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
						return driver.findElement(By.linkText(locatorValue));
					

					case "partialLinkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
						return driver.findElement(By.partialLinkText(locatorValue));
					

					case "tagName":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
						return driver.findElement(By.tagName(locatorValue));
						

					case "className":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
						return driver.findElement(By.className(locatorValue));
						

					case "cssSelector":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
						return driver.findElement(By.cssSelector(locatorValue));
						

					case "xpath":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						return driver.findElement(By.xpath(locatorValue));
						

					default:

						*//****** By default Xpath will be assumed *****//*
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						return driver.findElement(By.xpath(locatorValue));
					

					}

				} else {

					*//****** By default Xpath will be assumed *****//*
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));
					return driver.findElement(By.xpath(OR_SUS.getProperty(XpathKey)));

				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX function for " + XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObjectX function for " + XpathKey);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {

				fnpDesireScreenshot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey + "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}

	}
*/
	/*********** Wait till elemenet comes into clickable state ************/
	public static void fngWaitTillClickable(String XpathKey) throws Throwable {

		fngWaitForVisible(XpathKey);
		try {

			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			if (OR_SUS.getProperty(XpathKey).contains("~")) {

				String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
				String locatorValue = s1[1];
				String locator = s1[0];

				switch (locator) {
				case "id":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					break;

				case "name":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
					break;

				case "linkText":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
					break;

				case "partialLinkText":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
					break;

				case "tagName":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
					break;

				case "className":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
					break;

				case "cssSelector":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
					break;

				case "xpath":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
					break;

				default:
					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
					break;

				}

			} else {

				WebDriverWait wait3 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));

			}

		} catch (Exception e) {
			throw new Exception("fnpWaitTillClickable is failed for xpathKey [" + XpathKey + "]. ");
		}
	}
	
	/*********** wait till element get visible ************/
	public static void fngWaitForVisible(String XpathKey) throws Throwable {


		fnpIpulseDuringLoading();

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fngWaitForVisible for element ---" + XpathKey + " ---for chance --" + whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.NoSuchElementException.class);

				if (OR_SUS.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
					case "id":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
						break;

					case "name":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
						break;

					case "linkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
						break;

					case "partialLinkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
						break;

					case "tagName":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
						break;

					case "className":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
						break;

					case "cssSelector":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
						break;

					case "xpath":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					default:
						/****** By default Xpath will be assumed *****/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fngWaitForVisible for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath")) | (t.getMessage().contains("Timed out after"))) {
						throw new Exception(" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

		}

	}
	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 * 
	 * @return
	 *************/

	public static List<WebElement> fngGetORObject_list(String XpathKey, int maxTimeInSeconds) throws Exception {

		int retries = 0;
		while (true) {
			try {

				driver.manage().timeouts().implicitlyWait(maxTimeInSeconds, TimeUnit.SECONDS);

				if (OR_SUS.getProperty(XpathKey).contains("~")) {
					return fnpReturnWebElementAfterExtractLocatorandValue(OR_SUS.getProperty(XpathKey));
				} else {

					/****** By default Xpath will be assumed *****/
					return driver.findElements(By.xpath(OR_SUS.getProperty(XpathKey)));
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {

				// Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				if (retries < maxTimeInSeconds) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObject_list 2 arguments  function for " + XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {

				if (retries < 10) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 2 arguments  function for " + XpathKey);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < maxTimeInSeconds) {
					// Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In Throwable catch block of fnpGetORObject_list 2 arguments  function for " + XpathKey);

				} else {
					fnaTake_Screen_Shot("NotPresent" + XpathKey);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey + "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}

	}
	/**** Using in WO ISR *****************************************************/
	/**** First search then select first radio button in Lookup ****/
	public static void fngSearchNSelectFirstRadioBtn(String[][] lookup, int level) throws Throwable {


		/************** commented on 23-03-15 ********************/
		fngWaitForVisible("LookupFirstTextBox");
		/************** commented on 23-03-15 ********************/

		int lookupSelectionSize = lookup.length;
		String searchField_xpath;
		String conditionField_xpath;
		String valueField_xpath;
		String value;

		String goingToInsertThisValue;

		for (int j = 0; j < lookupSelectionSize; j++) {

			searchField_xpath = OR_SUS.getProperty("SearchFieldsInLookup_firstPart") + j + OR_SUS.getProperty("SearchFieldsInLookup_secondPart");
			conditionField_xpath = OR_SUS.getProperty("SelectConditionInLookup_firstPart") + j + OR_SUS.getProperty("SelectConditionInLookup_secondPart");
			valueField_xpath = OR_SUS.getProperty("ValueFieldsInLookup_firstPart") + j + OR_SUS.getProperty("ValueFieldsInLookup_secondPart").trim();

			fnpPFListModify_NOR(searchField_xpath, fnpremoveFormatting(lookup[j][0]));

			Thread.sleep(500);

			fnpGetORObjectX("lookupDialogTitle").click();

			Thread.sleep(500);

			fnpPFListModify_NOR(conditionField_xpath, fnpremoveFormatting(lookup[j][1]));

			Thread.sleep(500);

			fnpGetORObjectX("lookupDialogTitle").click();

			Thread.sleep(500);

			fnpGetORObjectX___NOR(valueField_xpath).sendKeys("");

			goingToInsertThisValue = fnpremoveFormatting(lookup[j][2]).trim();

			fnpType("", valueField_xpath, goingToInsertThisValue);
			if (lookupSelectionSize > 1) {
				Thread.sleep(10000); // /commented on 17-11-16 as it taking lot
										// of time...if failed then uncomment
										// this only
				// Thread.sleep(4000);
			} else {
				Thread.sleep(4000);
			}

			valueField_xpath = OR_SUS.getProperty("ValueFieldsInLookup_firstPart") + (j + 1) + OR_SUS.getProperty("ValueFieldsInLookup_secondPart").trim();
			Thread.sleep(500);
			driver.findElement(By.xpath(valueField_xpath)).click();
			Thread.sleep(500);

			Thread.sleep(500);

		}

		int beforeSearchRow = fngCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		fnpClick_OR("LookupSearchBtn");

		try {
			fnpWaitTillClickable("FirstRadioBtnInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]")).getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");

		fnpLoading_wait();

	}
	/**** First search then select first radio button in Lookup ****/
	public static void fngSearchNSelectFirstRadioBtn(int LKPTextBoxNo, String value, int level) throws Throwable {



		/************** commented on 23-03-15 ********************/
		fngWaitForVisible("LookupFirstTextBox_id");
		/************** commented on 23-03-15 ********************/

		if (value.trim().isEmpty()) {
			fnpClick_OR("FirstRadioBtnInLkpAFSch");

			return;
		}

		int beforeSearchRow = fngCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		if (LKPTextBoxNo == 1) {
			fnpPFListModify("FirstSelectConditionInLookup", "Equals");

			fnpGetORObjectX("LookupFirstTextBox_id").sendKeys("");

			fnpGetORObjectX("LookupFirstTextBox_id").sendKeys(value);

		}
		if (LKPTextBoxNo == 2) {
			fnpPFListModify("SecondSelectConditionInLookup", "Equals");
			fnpGetORObjectX("LookkupSecondTextBox_id").sendKeys("");
			fnpGetORObjectX("LookkupSecondTextBox_id").sendKeys(value);
		}

		fnpClick_OR("LookupSearchBtn_id");

		try {
			fnpWaitTillClickable("FirstRadioBtnInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]")).getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");
		fnpLoading_waitInsideDialogBox();

	}
	/*********** Function to count no. of rows in a Lookup table *************/
	public static int fngCountRowsIn_LOOKUPTable(String LookupRowsXpathKey) throws Exception {
		try {

			List<WebElement> elements = fngGetORObject_list(LookupRowsXpathKey, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			int NoOfRows = elements.size();
			return NoOfRows;

		} catch (Throwable t) {
			fnaTake_Screen_Shot("CountRowFailFrom" + LookupRowsXpathKey);
			throw new Exception("Unable to count rows from  Table having xpath [" + LookupRowsXpathKey + "],plz see screenshot [CountRowFailFrom" + LookupRowsXpathKey + SShots
					+ "]");

		}
	}
	/****
	 * To find the Row no in a table having paging + Also not throw error when
	 * not found as if not present in one page then may be present in next page
	 * , so not throw any error
	 ****/
	public static int fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage(String TableDataXpathName, String matchingName, int colNo) throws Throwable {

		int retries = 0;
		int resultRowNo = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = OR_SUS.getProperty(TableDataXpathName) + "/tr";

				int TotalRowCount = fnpGetORObject_list_NOR(xpathRow, 1).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);

					TotalRowCount = fnpGetORObject_list_NOR(xpathRow, 1).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (Value.equalsIgnoreCase(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 2) {
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");

					} else {

						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						// Thread.sleep(2000);
					}
				}
				return resultRowNo;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for " + matchingName);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);

				} else {
					String msg = t.getMessage();
					// throw new Exception(msg);
					fnpMymsg("@  ---resultRowNo is ---" + resultRowNo);
					fnpMymsg("@  ---this is not present in any row ---" + matchingName);

					return resultRowNo;
				}

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}
	}
	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static String fngGetAttribute_OR(String XpathKey, String attributeName) throws Exception {
		int retries = 0;

		while (true) {
			try {
				return fngGetORObjectX(XpathKey).getAttribute(attributeName);
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetText_OR function for " + XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				fnaTake_Screen_Shot("UnableToFetchUsingGetText" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + XpathKey + "] ,plz see screenshot [UnableToFetchUsingGetText" + XpathKey
						+ SShots + "]");

			}
		}

	}
	/*********** wait till element get visible ************/
	public static void fngWaitForVisible(String XpathKey, int MaxTimeInSeconds) throws Throwable {


		fnpIpulseDuringLoading();

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitForVisible for element ---" + XpathKey + " ---for chance --" + whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(MaxTimeInSeconds, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class);

				if (OR_SUS.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];
					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
											wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));

										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));

											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));

												} else {

													/******
													 * By default Xpath will be
													 * assumed
													 *****/
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));

												}
											}
										}
									}
								}
							}
						}
					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitForVisible for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {
					fnpMymsg("@  ---throwing exception after catch Throwbale block for whilecounter value--" + whileCounter);
					// throw new Exception(t.getMessage());

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath")) | (t.getMessage().contains("Timed out after"))) {
						throw new Exception(" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

		}

	}
	public static List<WebElement> fngGetORObject_list(String XpathKey, int maxTimeInSeconds, long maxTimeInMilliSeconds) throws Throwable {

		int retries = 0;
		while (true) {
			try {
				if ((XpathKey.trim().toLowerCase().contains("error")) | (XpathKey.trim().toLowerCase().contains("ProgressImageIcon"))
						| (XpathKey.trim().toLowerCase().contains("BlockedUIScreen"))) {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				} else {
					driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

				}

				if (OR_SUS.getProperty(XpathKey).contains("~")) {
					return fnpReturnWebElementAfterExtractLocatorandValue(OR_SUS.getProperty(XpathKey));

				} else {

					/****** By default Xpath will be assumed *****/
					return driver.findElements(By.xpath(OR_SUS.getProperty(XpathKey)));
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					// if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObject_list 3 arguments function for " + XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {

				if (retries < 10) {
					Thread.sleep(2000);

					fnpMymsg(" @  ---In InvalidSelectorException in ip addresss---" + fnpGetIPAddress() + " machine  for retries --" + retries);

					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 3 arguments  function for " + XpathKey);
					// continue;
				} else {

					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********Exception thrown in " + fnpGetIPAddress() + " machine************************************************");

					fnpMymsg(is.getMessage());
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********************************************************");
					String PageSourceText = driver.getPageSource().toLowerCase();
					fnpMymsg("@   ---********************************************************");

					// fnpMymsg(fnpFormatReplaceSpecailCharacters(PageSourceText));
					fnpMymsg("@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");

					fnpMymsg("@   ---********************************************************");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

					throw is;
				}
			} catch (Throwable t) {

				fnaTake_Screen_Shot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey + "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}

	}
	/**** Function clicking of an object (button etc) which is present in OR ****/
	public static void fngClick_InSAM_OR(String xpathkey) throws Throwable {

		
		fngCheckError_In_SAM(" before clicking -- "+xpathkey);
		
		
		int i = 0;
		
/*		
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		
		*/
		
		
		
		
		i = 0;
		while (fngCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

		}


		i = 0;
		while (fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")) {
			//Thread.sleep(1000);
			Thread.sleep(500);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath2'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

		}
		fngClick_OR_WithoutWait(xpathkey);
		fnaLoading_wait_In_SAM();
		
		i = 0;
		while (fngCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

		}
	}

	/***** check error in application page ***/
	public static void fngCheckError_In_SAM(String msg) throws Throwable {

		// fnpMymsg("@  ---running function fnpCheckError");
		if (fngCheckElementPresenceImmediately("SAM_errorMessage")) {

			fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("SAM_errorMessage");
			String errMsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("SAM_errorMessage").trim();

			int errorLength = errMsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}


		
		// fnpMymsg("@  ---running function fnpCheckError");
		if (fngCheckElementPresenceImmediately("SAM_errorImage")) {

			fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("SAM_errorMessage2");
			String errMsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("SAM_errorMessage2").trim();

			int errorLength = errMsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}
	}
	/***********
	 * To check element is present or not immmediately
	 * 
	 * @throws Throwable
	 ************/
	@SuppressWarnings("finally")
	public static boolean fngCheckElementPresenceImmediately(String XpathKey) {
		boolean result = false;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			if (fngGetORObject_list(XpathKey, 0, 1).size() > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Throwable t) {
			if (t.getMessage().contains("null")) {
				fnpMymsg("@@@@@@   ...some null value is passing.....may be this --" + XpathKey);
			}
			result = false;
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

			return result;
		}
	}
	/*********** To check element is displayed immediately or not *************/
	public static boolean fngCheckElementDisplayedImmediately(String XpathKey) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);


			List<WebElement> elementList = fngGetORObject_list(XpathKey, 0, 500);
			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}
	
	
	
	
	public static boolean fnaCheckElementDisplayedImmediately_WithoutSS(String XpathKey) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);


			List<WebElement> elementList = driver.findElements(By.xpath(OR_SUS.getProperty(XpathKey)));
			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}	
	
	
	/****
	 * Function clicking of an object (button etc) which is present in OR
	 * without handling loading Wait
	 ****/
	public static void fngClick_OR_WithoutWait(String xpathkey) throws Throwable {
		int retries = 0;

		int i = 0;
		while (fngCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		fngCheckError("");
		fngWaitForVisible(xpathkey);

		fngWaitTillVisiblityOfElementNClickable_OR(xpathkey);

		while (true) {
			try {
				fngGetORObjectX(xpathkey).click();
				fnpMymsg(" Clicked '" + xpathkey + "' .");
				break;

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpClick_OR function for " + xpathkey);
					// continue;
				} else {
					throw e;
				}
			}

			catch (org.openqa.selenium.WebDriverException w) {

				if ((w.getMessage().contains("not clickable at point")) | (w.getMessage().contains("unknown error:"))) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fngWorkAroundToClickbottomFooter();
						Thread.sleep(1000);
						fnpMymsg(retries + "In WebDriverException catch block of fnpClick_OR function for " + xpathkey);
						// continue;
					} else {
						throw w;
					}
				} else {
					throw w;
				}
			}

		}

		fngCheckError("");

	}
	
	/***** check error in application page ***/
	public static void fngCheckError(String msg) throws Throwable {

		fngLoading_wait_CheckLoadingIconOnlyIniPulse();
		if (fngCheckElementPresenceImmediately("ErrorMessage")) {

			fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			String errMsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage").trim();

			int errorLength = errMsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}

		// for Error Page
		if (fngCheckElementPresenceImmediately("Error_PageIniPulse")) {

			fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Error_PageIniPulse");
			String errMsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("Content_Of_Error_PageIniPulse").trim();

			int errorLength = errMsg.length();
			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}

		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

	}
	
	public static void fngLoading_wait_CheckLoadingIconOnlyIniPulse() throws Throwable {
		int i = 0;
		while (fngCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'BlockedUIScreen'------" + i);
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}
			

		}

		i = 0;
		while (fngCheckElementDisplayedImmediately("ProgressImageIcon")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}
			

		}
	}
	
	public static void fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(String XpathKey) throws Exception {
		WebElement requiredObj = null;
		try {
			for (int wait = 0; wait < 2; wait++) {

				if (fngGetORObject_list(XpathKey, 2).size() > 0) {

					List<WebElement> objList = fngGetORObject_list(XpathKey, 1);

					for (int i = 0; i < objList.size(); i++) {

						if ((objList.get(i).isDisplayed()) & (!((fnpReturnText_notCallItDirectly(objList.get(i)).equalsIgnoreCase(""))))) {
							requiredObj = objList.get(i);
							break;
						}

					}

				} else {
					/*******
					 * we donot want to throw error for mouse hover on error.
					 * This is special method
					 *******/
					// throw new
					// Exception("Element is not Visible having Xpath  [ "
					// + XpathKey + " ]");
				}
			} // for loop Closed

			if (requiredObj != null) {
				WebElement hoverElement = requiredObj;
				Actions action = new Actions(driver);
				try {
					action.moveToElement(hoverElement).perform();
				} catch (Throwable t) {
					// nothing to do
				}

				Thread.sleep(500);
			}
		} catch (Throwable t) {
			// nothing to do
		}
	}
	// Work Even when multiple Elements having same xpath, will return the text
	// from one having text present first
	public static String fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(String XpathKey) throws Throwable {
		WebElement requiredObj = null;
		String returnText = "";

		if (fngGetORObject_list(XpathKey, 0, 1).size() > 0) {

			List<WebElement> objList = fngGetORObject_list(XpathKey, 0, 100);

			fnpMymsg("@  --no. of error class is ----" + objList.size());
			String currentText = "";
			int somecounter=0;
			for (int i = 0; i < objList.size(); i++) {
				
				try{
				fnpMymsg("@  --no. of " + (i + 1) + "error class displayed ----" + (objList.get(i).isDisplayed()));
				currentText = fnpReturnText_notCallItDirectly(objList.get(i));
				}catch(org.openqa.selenium.StaleElementReferenceException s){ //This catch block added on 03-05-2018 as sometime getting staleelementexception
					//nothing to do
					
					if (somecounter<2) {
						somecounter++;
						Thread.sleep(300);
						objList = fngGetORObject_list(XpathKey, 0, 100);
						fnpMymsg("@  --no. of error class after staleElement or screen refreshed is ----" + objList.size());
						i=-1;
					}
				}
				
				
				
				
				
				
/*				try{
				currentText = fnpReturnText_notCallItDirectly(objList.get(i));
				}catch(org.openqa.selenium.StaleElementReferenceException s){
					currentText = "";
				}
				*/
				fnpMymsg("@  --no. of " + (i + 1) + " error class have text ----" + currentText);

				fnpMymsg("@  --no. of " + (i + 1) + "error class have text ----" + currentText);


				if (!(currentText.equalsIgnoreCase(""))) {
					requiredObj = objList.get(i);
					fnpMymsg("@  -- " + (i + 1) + "error class is our required object.");

					if (!(returnText.contains(currentText))) {
						returnText = returnText + "  " + currentText;
					}
					// returnText=returnText+"  "+currentText;
					// break;
				}

			}

		} else {
			/******
			 * This below throw line can be commented only in function using for
			 * error specially i.e.
			 * fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible
			 *****/
			// throw new Exception("Element is not Visible having Xpath  [ " +
			// XpathKey + " ]");
		}


		return returnText.trim();

	}
	
	/**** Wait till Element gets visible and in clickable state which is not in OR ****/
	public static void fngWaitTillVisiblityOfElementNClickable_OR(String XpathKey) throws Throwable {

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fngWaitTillVisiblityOfElementNClickable_OR for element ---" + XpathKey + " ---for chance --" + whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class).ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				if (OR_SUS.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
					case "id":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
						break;

					case "name":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
						break;

					case "linkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
						break;

					case "partialLinkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
						break;

					case "tagName":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
						break;

					case "className":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
						break;

					case "cssSelector":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
						break;

					case "xpath":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					default:
						/****** By default Xpath will be assumed *****/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fngWaitTillVisiblityOfElementNClickable_OR for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath")) | (t.getMessage().contains("Timed out after"))) {
						throw new Exception(" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

					// throw new Exception(t.getMessage());
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

		}

	}
	// Work around before clicking as sometime top menu AJAX open and scirpt
		// failed when unable to click the link behind
		public static void fngWorkAroundToClickbottomFooter() throws Throwable {
			try {
				if (fngCheckElementEnabledImmediately("FooterElement")) {
					fnpMymsg("@  - Footer element is enabled.");
					fngMouseHover("FooterElement");
					fngGetORObjectX("FooterElement").click();
				}
			} catch (Throwable t) {

			}

		}
		// To check element is displayed or not
		public static boolean fngCheckElementEnabledImmediately(String XpathKey) {

			int i = 0;
			try {

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				List<WebElement> elementList = fngGetORObject_list(XpathKey, 0, 1);

				for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

					WebElement webElement = (WebElement) iterator.next();
					if (webElement.isEnabled()) {
						// return true;
						i = 1;
						break;
					} else {
						// return false;
					}

				}

			} catch (Throwable t) {
				// return false;

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

			if (i == 0) {
				return false;

			} else {
				return true;
			}

		}
		/**** To mouse hover on an element ****/
		public static void fngMouseHover(String ORObjectName) throws Exception {

			fnpMymsg("@  ---just before mouse hover " + ORObjectName);

			int j = 0;
			while (true) {
				try {
					j++;
					WebElement requiredObj = null;

					if (fngGetORObject_list(ORObjectName, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 1) {

						List<WebElement> objList = fngGetORObject_list(ORObjectName, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

						for (int i = 0; i < objList.size(); i++) {
							if ((objList.get(i).isDisplayed())) {
								requiredObj = objList.get(i);
								break;
							}

						}
					} else {
						requiredObj = fngGetORObjectX(ORObjectName);
					}

					WebElement hoverElement = requiredObj;
					Actions action = new Actions(driver);
					action.moveToElement(hoverElement).perform();
					Thread.sleep(500);
					break;
				} catch (Throwable t) {
					// Thread.sleep(1000);//chhavi commented on 12-02-16
					fnpMymsg("@  ---in throw loop--" + j);
					if (j > 5) {
						break;

					}

				}
			}
			fnpMymsg("@  ---just after mouse hover " + ORObjectName);

		}
		
		/*********** Wait till main loading icon overs *************/
		public static void fngLoading_wait_In_SAM() throws Throwable {
			int i = 0;


			fngCheckError_In_SAM(" ");
			
			int iwhileCounter=0;
			while (true) {
				iwhileCounter++;
				if (  fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath1")  |  fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")  |  fngCheckElementDisplayedImmediately("SAM_loadingImageXpath") | fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath3") | fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath4") ) {
					fnpMymsg("@@@   main loading is visible in SAM - fnpLoading_wait_In_SAM--" + i);
					break;
				} else {

					if (iwhileCounter > 2) {
						break;
					}
					
					
					fnpMymsg("@@@    main loading icon is not visible  in SAM after " + (iwhileCounter ) + " seconds");
					Thread.sleep(1000);
					i++;
					
					
					
					
				}
			}
			i = 0;
			while (fngCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
				fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
				/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
				break;

			}*/
			
			
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

			}


			i = 0;
			while (fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")) {
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
				fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath2'------" + i);
				fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
				/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
				break;

			}*/
			
			
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

			}
			
			
			
			i = 0;
			while (fngCheckElementDisplayedImmediately("SAM_loadingImageXpath")) {
				fnpMymsg("@@@   while loop when loading is visible now inside - SAM_loadingImageXpath--" + i);
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
	/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
					break;

				}*/
				
				
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}

			}
			
			i = 0;
			while (fngCheckElementDisplayedImmediately("SAM_loadingImageXpath3")) {
				fnpMymsg("@@@   while loop when loading is visible now inside - SAM_loadingImageXpath3--" + i);
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
	/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
					break;

				}*/
				
				
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}

			}
			
			i = 0;
			while (fngCheckElementDisplayedImmediately("SAM_loadingImageXpath4")) {
				fnpMymsg("@@@   while loop when loading is visible now inside - SAM_loadingImageXpath4--" + i);
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
	/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
					break;

				}*/
				
				
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}

			}
			

		
			fngCheckError_In_SAM(" after loading ");
			Thread.sleep(2000);

		}
		
		
		public static void fngCommonProcessQuestionnairesSet(String questionnaireSet) throws Throwable{
			
			
			fnpMymsg("");
			fnpMymsg(" Now going to insert the values in Questionnaire .");
			//String NoOfSets[] = table.get("QuestionNAnswerSets").split("::");
			String NoOfSets[] = questionnaireSet.split("::");
			fnpMymsg("No. of question sets are ---" + NoOfSets.length);
			fnpMymsg("");
			fnpMymsg("");

			for (int j = 0; j < NoOfSets.length; j++) {
				if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
					// Thread.sleep(1000);
					fngWaitForVisible("Prop_Questionnaire_SaveNNextBtn");
				}
				fnpMymsg("***********************************************");
				String QuestionsSet = NoOfSets[j];
				fnpMymsg("Question set is--" + QuestionsSet);
				String NoOfQuestionsInCurrentSet[] = QuestionsSet.split(":");

				fnpMymsg("No. of questions are ---" + NoOfQuestionsInCurrentSet.length);
				fnpMymsg("");
				fnpMymsg("");
				for (int i = 0; i < NoOfQuestionsInCurrentSet.length; i++) {
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("Each question and its value are--" + NoOfQuestionsInCurrentSet[i]);
					String eachQuestion[] = NoOfQuestionsInCurrentSet[i].split(",");
					String QuestionNo = fnpremoveFormatting(eachQuestion[0]);
					fnpMymsg("Question no. is--" + QuestionNo);

					String AnswerValue = fnpremoveFormatting(eachQuestion[1]);
					fnpMymsg("Its answer is--" + AnswerValue);


					
					
					try{
						fngFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
					}catch(Throwable t){
						if (   (t.getMessage().contains("Fail due to Actual"))     &  (t.getMessage().contains("are not matched."))  )  {
							fnpMymsg("As value has not selected properly, so we are going to select it properly once again.");
							fngFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
						}else{
							throw t;
						}
					}
					
					
					
					
					

					//Thread.sleep(2000);//commented on 19th feb	
					Thread.sleep(500);

				}

				if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
					//Thread.sleep(1000);

					//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					WebElement saveNnextBtn = driver.findElement(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_SaveNNextBtn")));
					Actions action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					if (!browserName.equalsIgnoreCase("chrome")) {
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					}
				//	fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
					//fnpClickInDialog_OR("Prop_Questionnaire_SaveNNextBtn");
					driver.findElement(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_SaveNNextBtn"))).click();
					 Thread.sleep(8000);
				//	Thread.sleep(2000);
				}

			}
			
			

			if  (    (currentSuiteName.equalsIgnoreCase("SUS_suite"))   | (currentSuiteName.equalsIgnoreCase("SCFS_suite")) ) {             
				fngClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
			} else {
				fngClickInDialog_OR("Prop_QuestionnaireCloseBtn");
			}
			
			//}
	}
		//Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
		public static void fngFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(
																		String questNo,
																		String answervalue) throws Throwable {

			fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);

			String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
		//	String questXpath = ".//label[contains(normalize-space(text()),'" + questNo + " :')]";
		
			WebElement quest=null;
			try{
			  quest = driver.findElement(By.xpath(questXpath));
			}		
			catch(org.openqa.selenium.NoSuchElementException  nse){
				fnpMymsg("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
				throw new Exception("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
			}
			


			Actions action0 = new Actions(driver);
			 action0.moveToElement(quest).doubleClick().build().perform();
			//action0.moveToElement(fnpGetORObjectX___NOR(questXpath)).doubleClick().build().perform();
			Thread.sleep(1000);

			 quest = driver.findElement(By.xpath(questXpath));
		
				Actions action = new Actions(driver);

				/*
				 * action.moveToElement(quest).build().perform(); quest.sendKeys(Keys.ARROW_DOWN);
				 */
				action.moveToElement(quest).build().perform();
			//	quest.sendKeys(Keys.ARROW_DOWN);
				
				if (!browserName.equalsIgnoreCase("chrome")) 
					quest.sendKeys(Keys.ARROW_DOWN);


			
			//fnpMymsg(" Complete Question is this--" + fnpGetText_NOR(questXpath));
			//quest = fnpGetORObjectX___NOR(questXpath);
				 quest = driver.findElement(By.xpath(questXpath));
			String questid = quest.getAttribute("id");

			// String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo +
			// " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]";
			
			
	/*		
			
			String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
			String answerRadioXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
			String answerTextBoxXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";

	*/		
			
			
			String answerListXpath = ".//tr/td/label[starts-with(text(),'" + questNo+ " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
			
			
			String answerRadioXpath = ".//tr/td/label[starts-with(text(),'" + questNo
					+ " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
			String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
					+ " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";
		
			
			
			
			if (fnpCheckElementPresenceImmediately_NotInOR(answerListXpath)) {
				//fnpWaitForVisible_NotInOR(answerListXpath);

				fnpMymsg("List is present for Question--" + questNo);
				String p;
				WebElement oList;
				try {
					 oList = driver.findElement(By.xpath(answerListXpath));
					//oList = fnpGetORObjectX___NOR(answerListXpath);
					p = oList.getAttribute("id");
				} catch (StaleElementReferenceException s) {
					WebElement saveNnextBtn = fngGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					fnpMymsg("As question is not visible properly, so going to move down in If block");
					 action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					// Thread.sleep(2000);
					// Thread.sleep(500);
					// oList = driver.findElement(By.xpath(answerListXpath));
					oList = fnpGetORObjectX___NOR(answerListXpath);
					p = oList.getAttribute("id");

				}

			//	fnpMymsg("  ---id for list is-" + p);

				// String labelid = p + "_label";

				String pp = p.replace("_label", "");
				pp = pp.trim();
				String labelid = pp.trim() + "_label";
				// oList.click();
				int timer = 0;
				while (true) {
					try {
						// fnpWaitTillVisiblityOfElementNClickable(answerListXpath);
						 oList = driver.findElement(By.xpath(answerListXpath));
						//oList = fnpGetORObjectX___NOR(answerListXpath);
						oList.click();
					//	fnpLoading_wait();
						break;
					} catch (StaleElementReferenceException s) {
						Thread.sleep(1000);
						timer = timer + 1;
						if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						}
					}
				}
				Thread.sleep(1500);

				// String panelId = p + "_panel";
				String panelId = pp.trim() + "_panel";
				String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";
				int iCounter = 1;
				while (true) {
					try {
						// fnpWaitTillVisiblityOfElementNClickable(listValue);---old
						// fnpMouseHover_NotInOR(listValue);--were fine
						// Thread.sleep(2000);--were fine
						// driver.findElement(By.xpath(listValue)).click();--old

						// List<WebElement> objectlistValues=driver.findElement(By.xpath(listValue)).findElements(By.tagName("li"));
						List<WebElement> objectlistValues = driver.findElement(By.xpath(".//*[@id='" + panelId + "']")).findElements(By.tagName("li"));
						boolean ValueMatched = false;
						;
						for (WebElement dd_value : objectlistValues) {
							Actions act = new Actions(driver);
							act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
						//	//System.out.println("  ------" + dd_value.getText());
							if (dd_value.getText().equals(answervalue)) {
								//Thread.sleep(500);
								Thread.sleep(1000);
								// fnpWaitForVisible_NotInOR(listValue);
								
								if (browserName.equalsIgnoreCase("IE")) {
									act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
									Thread.sleep(200);
									act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();

								}



								//fnpWaitTillVisiblityOfElementNClickable(listValue);
								dd_value.click();
								fnpLoading_wait();
								ValueMatched = true;
								break;
							}

						}
						if (ValueMatched == false) {
							throw new Exception("Excel value --'" + answervalue + "'  is not matched with DropDown Value.");
						}

						// Thread.sleep(2000);
						// Thread.sleep(500);
						// Thread.sleep(1000);
						break;
					} catch (Throwable t) {
						WebElement saveNnextBtn = fngGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
						Actions action11 = new Actions(driver);
						action11.moveToElement(saveNnextBtn).build().perform();
					//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							quest.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						driver.findElement(By.id(p)).click();
						fnpLoading_wait();
					//	Thread.sleep(1000);
					//	fnpLoading_wait();
						if (iCounter < 3) {
							iCounter++;
						} else {
							break;

						}
					}
				}

				// Thread.sleep(1000);
				String selectedlistValue = "";
				try {
					selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
				} catch (Throwable t) {
					// Thread.sleep(5000);
					Thread.sleep(2000);
					selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
				}
				fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

				Assert.assertEquals(selectedlistValue, answervalue, "Fail due to Actual '" + selectedlistValue + "' and expected '" + answervalue + "' are not matched.");

				fnpMymsg("");
				fnpMymsg("");

			}

			if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
				fnpMymsg("Radio is present for Question--" + questNo);

				String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td/label[contains(text(),'" + answervalue + "')]";
				int whileloopCount = 0;
				while (true) {

					// Thread.sleep(2000); commented on 19th feb
					String checkedRadioLabelForAttribute;
					String checkedLabel = null;

							try {
								WebElement fac = driver.findElement(By.xpath(answerRadioXpathLabel));
								//WebElement fac = fnpGetORObjectX___NOR(answerRadioXpathLabel);
								 action = new Actions(driver);
								action.moveToElement(fac).doubleClick().build().perform();
							//	 Thread.sleep(1000);
								fnpLoading_wait();
							//	 Thread.sleep(1000); //commented on 19th feb
								//Thread.sleep(1000);
							//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
							//	checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");
										while (true) {
											Thread.sleep(500);
											checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
											//checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");						
											String checkedRadioXpath2 = ".//td/div/div/input[@id='" + checkedRadioLabelForAttribute + "']";
											WebElement checkedRadio = driver.findElement(By.xpath(checkedRadioXpath2));
										//	WebElement checkedRadio = fnpGetORObjectX___NOR(checkedRadioXpath2);
											checkedLabel = checkedRadio.getAttribute("checked");
											fnpMymsg("Checked attribute value is--" + checkedLabel);

											if (checkedLabel != null) {
												break;
					
											} else {
					
												whileloopCount++;
												if (whileloopCount < 3) {
													fnpMymsg("@@@    ---As checked attribute value is null so going to double click again--");
													action.moveToElement(fac).doubleClick().build().perform();
													//Thread.sleep(2000);
													fnpLoading_wait();
												} else {
													break;
												}
											}
											}

											if (checkedLabel != null) {
												if (checkedLabel.equalsIgnoreCase("true")) {
													fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
													break;
												} else {
													// continue;
													fnpMymsg("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
													throw new Exception("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
												}
						
											} else {
												// continue;
												fnpMymsg("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
												throw new Exception("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											}

										} catch (StaleElementReferenceException s) {
											whileloopCount++;
											if (whileloopCount < 3) {
												Thread.sleep(2000);
											} else {
												throw new Exception(s.getMessage());
											}
										}

					catch (Throwable t) {
						fnpMymsg("********   Watch here in catch block is ---" + t.getMessage());
						// WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
						
					//	WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNCloseBtn");// for last page if error thrown as saveNNext button not
																										// enabled there
						
						
						
						
						WebElement saveNnextBtn = null;
						
						if (fngCheckElementDisplayedImmediately( "Prop_Questionnaire_SaveNCloseBtn")) {
							saveNnextBtn = fngGetORObjectX( "Prop_Questionnaire_SaveNCloseBtn");// for
							action = new Actions(driver);
							action.moveToElement(saveNnextBtn).build().perform();
						} else {
							if (fngCheckElementDisplayedImmediately( "Prop_QuestionnaireCloseBtnInISR")) {
								saveNnextBtn = fngGetORObjectX("Prop_QuestionnaireCloseBtnInISR");// for
								action = new Actions(driver);
								action.moveToElement(saveNnextBtn).build().perform();
							}
							
						}
						
						
						
	/*					
						action = new Actions(driver);
						action.moveToElement(saveNnextBtn).build().perform();
						*/
						
						
					//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							quest.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						// saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						// Thread.sleep(1000);

						whileloopCount++;
						if (whileloopCount < 3) {
							// Thread.sleep(2000);
							// continue;
							Thread.sleep(2000);
						} else {
							throw new Exception(t.getMessage() + "\n\n Error thrown in question no.--" + questNo + " and error is --" + t.getMessage());
						}
					}
				}
			}

			if (fnpCheckElementPresenceImmediately_NotInOR(answerTextBoxXpath)) {

				String insertedValue;
				fnpMymsg("Text box is present for Question--" + questNo);
			//	fnpWaitForVisible_NotInOR(answerTextBoxXpath);
				driver.findElement(By.xpath(answerTextBoxXpath)).sendKeys(answervalue);
			//	fnpType(null,answerTextBoxXpath,answervalue );
				Thread.sleep(1000);

				// commented on 19th feb
				/*
				 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) { driver.findElement(By.xpath(answerTextBoxXpath)).click(); Thread.sleep(1000); }
				 */
				fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

				insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");



				int whileloopCountTxtbox = 0;
				while (!insertedValue.equalsIgnoreCase(answervalue)) {
					whileloopCountTxtbox++;
					if (whileloopCountTxtbox < 3) {
						Thread.sleep(2000);
						insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
					} else {
						break;
					}

				}
				fnpMymsg("Inserted value in text box is--" + insertedValue);
				Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
				fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

			}

		}
		/****
		 * Function clicking of an object (button etc) which is present in inner
		 * dialog boxes in application
		 ****/
		public static void fngClickInDialog_OR(String xpathkey) throws Throwable {
			int retries = 0;

			int i = 0;
			while (fngCheckElementPresenceImmediately("BlockedUIScreen")) {
				Thread.sleep(1000);

				i++;
				if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
					break;

				}

			}
			//fnpCheckError("");
			fngWaitTillVisiblityOfElementNClickable_OR(xpathkey);


			while (true) {
				try {
					Thread.sleep(1000);// put this on 4-3-16 to guesss to avoid we
										// are sorry in dialog, if later it solved
										// or still sometime showing we are sorry
										// error then remove it later
					fngGetORObjectX(xpathkey).click();
					fngLoading_waitInsideDialogBox();

					i = 0;
					while (fngCheckElementPresenceImmediately("BlockedUIScreen")) {
						Thread.sleep(1000);
						i++;
						if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {

							break;

						}

					}

					fnpMymsg(" Clicked '" + xpathkey + "' .");
					break;

				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpClickInDialog_OR function for " + xpathkey);
						// continue;
					} else {
						throw e;
					}
				}

				catch (org.openqa.selenium.WebDriverException w) {

					if ((w.getMessage().contains("not clickable at point")) | (w.getMessage().contains("unknown error:"))) {
						if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							// if (retries <1) {
							retries++;
							Thread.sleep(1000);
							fnpMymsg(retries + "   In WebDriverException catch block of fnpClickInDialog_OR function for " + xpathkey);
							// continue;
						} else {
							throw w;
						}
					} else {
						throw w;
					}
				}

			}

			fnpCheckError("");
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

		}
		/***********
		 * Function to find and return the object using Xpath which are present in
		 * OR
		 * 
		 * @throws Throwable
		 *************/
		public static WebElement fngGetORObjectX(String XpathKey) throws Throwable {

			// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

			int retries = 0;
			while (true) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
							.ignoring(org.openqa.selenium.StaleElementReferenceException.class).ignoring(org.openqa.selenium.WebDriverException.class)
							.ignoring(org.openqa.selenium.StaleElementReferenceException.class);

					if (OR_SUS.getProperty(XpathKey).contains("~")) {

						String[] s1 = OR_SUS.getProperty(XpathKey).split("~");
						String locatorValue = s1[1];
						String locator = s1[0];

						switch (locator) {
						case "id":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));// xpath
							wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
							return driver.findElement(By.id(locatorValue));
						

						case "name":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));// id
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
							return driver.findElement(By.name(locatorValue));
						

						case "linkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
							return driver.findElement(By.linkText(locatorValue));
						

						case "partialLinkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
							return driver.findElement(By.partialLinkText(locatorValue));
						

						case "tagName":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
							return driver.findElement(By.tagName(locatorValue));
							

						case "className":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
							return driver.findElement(By.className(locatorValue));
							

						case "cssSelector":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
							return driver.findElement(By.cssSelector(locatorValue));
							

						case "xpath":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							return driver.findElement(By.xpath(locatorValue));
							

						default:

							/****** By default Xpath will be assumed *****/
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							return driver.findElement(By.xpath(locatorValue));
						

						}

					} else {

						/****** By default Xpath will be assumed *****/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty(XpathKey))));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_SUS.getProperty(XpathKey))));
						return driver.findElement(By.xpath(OR_SUS.getProperty(XpathKey)));

					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						Thread.sleep(1000);
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX function for " + XpathKey);
						// continue;
					} else {
						throw s;
					}
				}

				catch (org.openqa.selenium.InvalidSelectorException is) {
					if (retries < 3) {
						Thread.sleep(1000);
						retries++;
						fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObjectX function for " + XpathKey);
						// continue;
					} else {
						throw is;
					}
				} catch (Throwable t) {

					fnaTake_Screen_Shot("NotPresent" + XpathKey);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey + "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

				}

				finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}

		}

		/**** It is using CSS Selector not xpath ******/
		/********* Basically it return column sequence number not index *******/
		public static int fngFindColumnIndex(String TableHeaderXpathName, String columnName) throws Throwable {
			int retries = 0;

			while (true) {

				retries++;

				try {
					int colIndex = 0;
					String[] idString = OR_SUS.getProperty(TableHeaderXpathName).split("'", -1);
					String xpathHeaderCss = "table thead[id='" + idString[1] + "'] tr th";
					int HeaderCount = driver.findElements(By.cssSelector(xpathHeaderCss)).size();
					for (int i = 1; i < HeaderCount + 1; i++) {
						String CssExpression = "table thead[id='" + idString[1] + "'] tr th:nth-child(" + i + ")";
						String headerValue = driver.findElement(By.cssSelector(CssExpression)).getText().trim();
						String headerValueWithoutTrim = driver.findElement(By.cssSelector(CssExpression)).getText();
						if (columnName.equalsIgnoreCase(headerValue) | columnName.equalsIgnoreCase(headerValueWithoutTrim) | columnName.trim().equalsIgnoreCase(headerValue.trim())) {
							colIndex = i;
							fnpMymsg("@@@@@@@   -----col no is ---for '" + headerValue + "' is -----" + colIndex);
							break;

						}

					}

					if (colIndex > 0) {
						return colIndex;
					} else {

						if (retries > 4) {
							throw new Exception("Col no. is not calculated for -- '" + columnName + "' for table having xpath ---'" + TableHeaderXpathName + "'.");
						} else {
							// going again in loop to calculate again
							// fnpLoading_wait();
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries > 4) {
						throw new Exception("StaleElementReferenceException is thrown ---Col no. is not calculated for -- '" + columnName + "' for table having xpath ---'"
								+ TableHeaderXpathName + "'.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
					}
				}

				catch (Throwable t) {
					if (retries < 2) {
						fnpMymsg("In catch block for try chance -" + retries);
						Thread.sleep(1000);
						// fnpLoading_wait();
					} else {
						String msg = t.getMessage();
						throw new Exception(msg);
					}

				}

			}
		}
		/**** To find the Row no in a table ****/
		public static int fngFindRow(String TableDataXpathName, String matchingName, int colNo) throws Throwable {

			int retries = 0;
			while (true) {
				try {

					retries++;
					fngWaitForVisible(TableDataXpathName);
					String xpathRow = OR_SUS.getProperty(TableDataXpathName) + "/tr";
					int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					int rowcountJ = 0;
					while (TotalRowCount == 0) {
						rowcountJ++;
						Thread.sleep(1000);
						TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
						if (rowcountJ > 2) {
							break;
						}

					}

					int resultRowNo = 0;
					// int resultColNo = 0;
					String xpathExpression = null;
					String Value = null;
					for (int i = 1; i < TotalRowCount + 1; i++) {
						xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
						//Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
						
						//12-04-2018 using below in try block because sometime (as in case of iAg at snapshot tab) in middle tr does not have td tag directly
						try{
							driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
							Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
						}catch(Throwable t){
							Value="";
							fnpMymsg("************************************************************");
							fnpMymsg("Getting some error for getting data. Error is ---"+t.getMessage());
							fnpMymsg("************************************************************");
						}finally{
							driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
						}
						
						
						if (Value.equalsIgnoreCase(matchingName)) {
							resultRowNo = i;
							break;

						}

					}

					if (resultRowNo > 0) {
						return resultRowNo;
					} else {

						if (retries > 4) {
							throw new Exception("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
						} else {
							// going again in loop to calculate again
							// fnpLoading_wait();
							fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
									+ "' . So going to calculate again");
							Thread.sleep(2000);
						}
					}

				}

				catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for " + matchingName);
						continue;
					} else {
						throw e;
					}

				} catch (org.openqa.selenium.InvalidSelectorException is) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for " + matchingName);
						continue;
					} else {
						throw is;
					}
				} catch (Throwable t) {
					if (retries < 2) {
						fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
						Thread.sleep(1000);
						// fnpLoading_wait();
					} else {
						String msg = t.getMessage();
						throw new Exception(msg);
					}

				}
			}
		}
		/*********** Wait till inner loading icon overs *************/
		public static void fngLoading_waitInsideDialogBox() throws Throwable {
			int i = 0;

			while (true) {
				if (fngCheckElementDisplayedImmediately("LoadingImg")) {

					fnpMymsg("@@@   loading is visible - fnpLoading_waitInsideDialogBox--" + i);

					break;
				} else {
					fnpMymsg("@@@    waiting for loading visible inside - fnpLoading_waitInsideDialogBox--" + i);

					Thread.sleep(1000);
					i++;
					if (i > 3) {

						break;
					}
				}
			}

			i = 0;
			while (fngCheckElementDisplayedImmediately("LoadingImg")) {
				fnpMymsg("@@@   while loop when loading is visible now inside - fnpLoading_waitInsideDialogBox--" + i);
				Thread.sleep(1000);
				i++;
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}
				

			}

			i = 0;
			while (fngCheckElementDisplayedImmediately("ProgressImageIcon")) {
				Thread.sleep(1000);
				i++;
				fnpMymsg("@  ---while loop when---fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}
				

			}

			fngCheckError(" after loading ");

		}
		/***********
		 * Function to find and return the object using Xpath which are present in
		 * OR
		 *************/
		public static String fngGetText_OR(String XpathKey) throws Exception {
			int retries = 0;

			while (true) {
				try {

					return fngGetText_OR_EvenMultipleObjectsHavingSameXpath(XpathKey);
				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpGetText_OR function for " + XpathKey);
						// continue;
					} else {
						throw s;
					}
				}

				catch (org.openqa.selenium.InvalidSelectorException is) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpMymsg(retries + "In InvalidSelectorException catch block of fngGetText_OR function for " + XpathKey);
						// continue;
					} else {
						throw is;
					}
				}

				catch (Throwable t) {
					fnaTake_Screen_Shot("UnableToFetchUsingGetText" + XpathKey);
					throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + XpathKey + "] ,plz see screenshot [UnableToFetchUsingGetText" + XpathKey
							+ SShots + "]");

				}
			}

		}
		// using this taken from grid
		public static void fngPFList(String XpathKey, String XpathOptionsKey, String value) throws Throwable {

			value = value.trim();
			String originalValue = value;
			//7 June 2021
			/*String defaultValue = fngGetText_OR(XpathKey);
			if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
				fnpMymsg("@ - default value is same as expected, so returning back.");
				return;
			}*/ 

			String tagname = null;
			boolean ValueMatched = false;
			boolean found = false;
			boolean selectedProperly = false;

			int intWhileCounter = 0;
			while (true) {
				intWhileCounter++;
				try {

					tagname = null;
					ValueMatched = false;
					found = false;

					String temp = originalValue;
					value = originalValue;
					fnpMymsg("Going to select this value ---'" + value + "'  .");
					fngWaitForVisible(XpathKey);
					fngWaitTillVisiblityOfElementNClickable_OR(XpathKey);


					if (!(fngCheckElementDisplayedImmediately(XpathOptionsKey))) {
						fnpMymsg("Here assumption is that list is not expanded.");
						fngClick_OR_WithoutWait(XpathKey);
					} else {
						// This will work when try again in 2nd chance, first close
						// already opened one and then click again to expand it.
						fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
						fngClick_OR_WithoutWait(XpathKey);
						Thread.sleep(1000);
						fngClick_OR_WithoutWait(XpathKey);
					}

					Thread.sleep(500);
					String listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
					if (fngCheckElementDisplayed_NOR(listValue, 1)) {
						// nothing to do
					} else {
						fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
						String new_value = value.toLowerCase();
						listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
						
						if (fngCheckElementDisplayedImmediately_NOR(listValue)) {
							// nothing to do
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value + "'  is not present as it is -- so going to make its first digit in uppercase.");

							new_value = fngReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
							value = new_value;
							listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
							if (fngCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value + "'  is not present as it is -- so going to make its all letters in upper case.");
								new_value = value.toUpperCase();
								listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
								if (fngCheckElementDisplayedImmediately_NOR(listValue)) {
									value = new_value;
								} else {
									fnpMymsg("Now again this value '" + new_value
											+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '" + temp + "'  .");
									value = temp; // to check once again (once more
													// time) the initial value as it
													// is
								}
							}

						}

					}

					listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
					fnpMymsg("@ - going to click expected option value --" + value);


					listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
					
					fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																													// change
																													// on
																													// 11-09-17

					found = true;
					fnpMymsg("@ -  clicked expected option value  in try block.");

					Thread.sleep(1000);


					fngWorkAroundToClickbottomFooter();

					fnpMymsg("@ -  Now going to fetch the selected value.");
					String FinalSelectedValue = fngGetText_OR(XpathKey).trim();
					fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
					String value2 = StringUtils.normalizeSpace(value);

					if (!(((FinalSelectedValue.contains(value))) | ((FinalSelectedValue.contains(value2))))) {
						fnpMymsg("@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value + "' and actual is --'" + FinalSelectedValue + "' .");
						throw new Exception(value + "'  is NOT  selected properly in  DropDown as expected is" + "--'" + value + "' and actual is --'" + FinalSelectedValue + "' .");
					} else {
						fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value + "' and actual is --'" + FinalSelectedValue + "' .");
						selectedProperly = true;
						break;
					}

				} catch (Throwable t) {
					fnpMymsg("@ - fngPFList is failed due to some error but try again in catch block. Previous error caught was ---" + t.getMessage());
					Thread.sleep(2000);
					if (intWhileCounter > 3) {
						fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----" + intWhileCounter);
						break;
					} else {
						// again in while loop
						fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
					}

				}

			}

			if (!selectedProperly) {

				throw new Exception("\n\n\n   OR_SUS  might be Value [" + value + "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
			} else {
				// nothing for now...
			}

		}
		// using this taken from grid
				public static void fngPFListIndustryCode(String XpathKey, String XpathOptionsKey, String value) throws Throwable {

					value = value.trim();
					String originalValue = value;
					//7 June 2021
					/*String defaultValue = fngGetText_OR(XpathKey);
					if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
						fnpMymsg("@ - default value is same as expected, so returning back.");
						return;
					}*/ 
					//fngClick_OR(XpathKey);
					
					String tagname = null;
					boolean ValueMatched = false;
					boolean found = false;
					boolean selectedProperly = false;

					int intWhileCounter = 0;
					while (true) {
						intWhileCounter++;
						try {

							tagname = null;
							ValueMatched = false;
							found = false;

							String temp = originalValue;
							value = originalValue;
							fnpMymsg("Going to select this value ---'" + value + "'  .");
							fngWaitForVisible(XpathKey);
							fngWaitTillVisiblityOfElementNClickable_OR(XpathKey);


							if (!(fngCheckElementDisplayedImmediately(XpathOptionsKey))) {
								fnpMymsg("Here assumption is that list is not expanded.");
								fngClick_OR_WithoutWait(XpathKey);
							} else {
								// This will work when try again in 2nd chance, first close
								// already opened one and then click again to expand it.
								fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
								fngClick_OR_WithoutWait(XpathKey);
								Thread.sleep(1000);
								fngClick_OR_WithoutWait(XpathKey);
							}

							Thread.sleep(500);
							String listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li/label[contains(text(),'" + value + "')]";
							if (fngCheckElementDisplayed_NOR(listValue, 1)) {
								// nothing to do
							} else {
								fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
								String new_value = value.toLowerCase();
								listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li/label[contains(text(),'" + new_value + "')]";
								
								if (fngCheckElementDisplayedImmediately_NOR(listValue)) {
									// nothing to do
									value = new_value;
								} else {
									fnpMymsg("Now again this value '" + new_value + "'  is not present as it is -- so going to make its first digit in uppercase.");

									new_value = fngReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
									value = new_value;
									listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li/label[contains(text(),'" + new_value + "')]";
									if (fngCheckElementDisplayedImmediately_NOR(listValue)) {
										value = new_value;
									} else {
										fnpMymsg("Now again this value '" + new_value + "'  is not present as it is -- so going to make its all letters in upper case.");
										new_value = value.toUpperCase();
										listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li/label[contains(text(),'" + new_value + "')]";
										if (fngCheckElementDisplayedImmediately_NOR(listValue)) {
											value = new_value;
										} else {
											fnpMymsg("Now again this value '" + new_value
													+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '" + temp + "'  .");
											value = temp; // to check once again (once more
															// time) the initial value as it
															// is
										}
									}

								}

							}

							listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li/label[contains(text(),'" + value + "')]";
							fnpMymsg("@ - going to click expected option value --" + value);


							listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li/label[contains(text(),'" + value + "')]";
							
							fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																															// change
																															// on
																															// 11-09-17

							found = true;
							fnpMymsg("@ -  clicked expected option value  in try block.");

							Thread.sleep(1000);


							fngWorkAroundToClickbottomFooter();

							fnpMymsg("@ -  Now going to fetch the selected value.");
							String FinalSelectedValue = fngGetText_OR(XpathKey).trim();
							fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
							String value2 = StringUtils.normalizeSpace(value);

							if (!(((FinalSelectedValue.contains(value))) | ((FinalSelectedValue.contains(value2))))) {
								fnpMymsg("@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value + "' and actual is --'" + FinalSelectedValue + "' .");
								throw new Exception(value + "'  is NOT  selected properly in  DropDown as expected is" + "--'" + value + "' and actual is --'" + FinalSelectedValue + "' .");
							} else {
								fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value + "' and actual is --'" + FinalSelectedValue + "' .");
								selectedProperly = true;
								break;
							}

						} catch (Throwable t) {
							fnpMymsg("@ - fngPFList is failed due to some error but try again in catch block. Previous error caught was ---" + t.getMessage());
							Thread.sleep(2000);
							if (intWhileCounter > 3) {
								fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----" + intWhileCounter);
								break;
							} else {
								// again in while loop
								fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
							}

						}

					}

					if (!selectedProperly) {

						throw new Exception("\n\n\n   OR_SUS  might be Value [" + value + "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
					} else {
						// nothing for now...
					}

				}
		// Work Even when multiple Elements having same xpath, will return the text
		// from one having text present first
		public static String fngGetText_OR_EvenMultipleObjectsHavingSameXpath(String XpathKey) throws Exception {
			WebElement requiredObj = null;
			String returnText = "";

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

			if (fngGetORObject_list(XpathKey, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {
				List<WebElement> objList = fngGetORObject_list(XpathKey, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				String currentText = "";
				for (int i = 0; i < objList.size(); i++) {
					currentText = fnpReturnText_notCallItDirectly(objList.get(i));
					// fnpMymsg("@  --no. of "+(i+1)+" error class have text ----"+returnText);

					if ((objList.get(i).isDisplayed()) & (!(currentText.equalsIgnoreCase("")))) {
						requiredObj = objList.get(i);
						returnText = returnText + currentText;
						// break;
					}

				}

			} else {

				throw new Exception("Element is not Visible having Xpath  [ " + XpathKey + " ]");
			}

			return returnText.trim();

		}
		/***********
		 * To select the value from prime faces list that contains matching value in
		 * li
		 *************/
		public static void fngPFListByLiNo(String XpathKey, String XpathOptionsKey, int liNo) throws Throwable {

			boolean selectedProperly = false;
			String finalValueToBeSelect = "";

			int intWhileCounter = 0;
			while (true) {
				intWhileCounter++;

				try {
					fngWaitForVisible(XpathKey);


					if (!(fngCheckElementDisplayedImmediately(XpathOptionsKey))) {
						fnpMymsg("Here assumption is that list is not expanded.");
						fngClick_OR_WithoutWait(XpathKey);
						Thread.sleep(500);
					} else {

						fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
						fngClick_OR_WithoutWait(XpathKey); // closing here
						Thread.sleep(1000);
						fngClick_OR_WithoutWait(XpathKey); // re-opening here
					}

					int totalValues = driver.findElements(By.xpath(OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li")).size();
					if (!(totalValues > 0)) {
						throw new Exception("Drop down is blank --" + XpathKey);

					}

					if ((totalValues == 1)) {
						String onlyGivenValue = driver.findElement(By.xpath(OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[1]")).getText();
						if ((onlyGivenValue.toLowerCase().contains("select")) | (onlyGivenValue.trim().equalsIgnoreCase(""))) {
							throw new Exception("Drop down is blank --" + XpathKey);
						}

					}

					String listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo + "]";

					fnpWaitTillVisiblityOfElementNClickable_NOR_Immediately(listValue);


					String valueToBeSelect = fnpGetText_NOR(listValue);
					if ((valueToBeSelect.toLowerCase().contains("select")) | (valueToBeSelect.trim().equalsIgnoreCase(""))) {
						listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[" + (liNo + 1) + "]";
					} else {
						listValue = OR_SUS.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo + "]";
					}

					finalValueToBeSelect = driver.findElement(By.xpath(listValue)).getText();
					driver.findElement(By.xpath(listValue)).click();

					Thread.sleep(1000);

					fnpMymsg("@ -  Now going to fetch the selected value.");
					String FinalSelectedValue = fngGetText_OR(XpathKey).trim();
					fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
					String value2 = StringUtils.normalizeSpace(FinalSelectedValue);


					if (!(((finalValueToBeSelect.contains(FinalSelectedValue))) | ((finalValueToBeSelect.contains(value2))))) {
						fnpMymsg("@ - '" + finalValueToBeSelect + "'  is NOT selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .");
						throw new Exception(finalValueToBeSelect + "'  is NOT  selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .");
					} else {
						fnpMymsg("@ - '" + finalValueToBeSelect + "'  is  selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .");
						selectedProperly = true;
						break;
					}

				} catch (Throwable t) {
					fnpMymsg("@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---" + t.getMessage());
					Thread.sleep(2000);
					if (intWhileCounter > 3) {
						fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----" + intWhileCounter);
						break;
					} else {
						// again in while loop
						fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
					}

				}

			}

			if (!selectedProperly) {
				throw new Exception("\n\n\n   And/OR  might be Value [" + finalValueToBeSelect + "] is not present in List ,plz see screenshot [ValueMissingInList"
						+ finalValueToBeSelect + SShots + "]");
			} else {
				// nothing for now...
			}

		}
		/*********** To check element is displayed immediately or not *************/
		public static boolean fngCheckElementDisplayed(String XpathKey, int maxtimeInSeconds) {

			int i = 0;
			try {

				driver.manage().timeouts().implicitlyWait(maxtimeInSeconds, TimeUnit.SECONDS);
				List<WebElement> elementList = fngGetORObject_list(XpathKey, maxtimeInSeconds);

				for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

					WebElement webElement = (WebElement) iterator.next();
					if (webElement.isDisplayed()) {
						// return true;
						i = 1;
						break;
					} else {
						// return false;
					}

				}

			} catch (Throwable t) {
				// return false;

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

			if (i == 0) {
				return false;

			} else {
				return true;
			}

		}
		// Picking date current day and Current Month for String Passing year By
		// Selecting values from months and years DropDpwn.
		public static void fngCalendarDatePicker_BySelectValues_From_NextAndBack(String stringDate) throws Throwable {
			try {

				Date todayDate = new Date();
				SimpleDateFormat todayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				String todayDateInStringFormat = todayDateFormat.format(todayDate);

				String CurrentDay = null;

				SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");


				Date dat = sdfmt1.parse(stringDate);
				Calendar c = Calendar.getInstance();
				c.setTime(dat);

				int year = c.get(Calendar.YEAR);
		

				int dom = c.get(Calendar.DAY_OF_MONTH);
			

				SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM");
				String monthName = sdfmt2.format(dat);

		

				String actualYearInStringFormat = fngGetText_OR("CalendarHeaderYear_xpath");
				int actualyear = Integer.parseInt(actualYearInStringFormat);

				while (actualyear < year) {
					fngClick_OR_WithoutWait("CalendarNextIcon");
					Thread.sleep(1000);
					actualYearInStringFormat = fngGetText_OR("CalendarHeaderYear_xpath");
					actualyear = Integer.parseInt(actualYearInStringFormat);
				}

				while (actualyear > year) {
					fngClick_OR_WithoutWait("CalendarPrevIcon");
					Thread.sleep(1000);
					actualYearInStringFormat = fngGetText_OR("CalendarHeaderYear_xpath");
					actualyear = Integer.parseInt(actualYearInStringFormat);
				}

				String actualMonthInStringFormat = fngGetText_OR("CalendarHeaderMonth_xpath");
				SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
				Date actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);

				SimpleDateFormat formonth = new SimpleDateFormat("dd-MMM-yyyy");
				Date datformonth = formonth.parse(stringDate);
				// Date datformonth=monthFormat.parse(stringDate);
				String datformonthString = monthFormat.format(datformonth);
				Date datformonth2 = monthFormat.parse(datformonthString);
				Date expectedMonthInDateFormat = datformonth2;

				while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) > 0) {
					fngClick_OR_WithoutWait("CalendarNextIcon");
					Thread.sleep(1000);
					actualMonthInStringFormat = fngGetText_OR("CalendarHeaderMonth_xpath");
					actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
				}

				while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) < 0) {
					fngClick_OR_WithoutWait("CalendarPrevIcon");
					Thread.sleep(1000);
					actualMonthInStringFormat = fngGetText_OR("CalendarHeaderMonth_xpath");
					actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
				}

				String DatePickForWhichDay = Integer.toString(dom);
				if (DatePickForWhichDay != null) {
					CurrentDay = DatePickForWhichDay;
				} else {
					Calendar cal = Calendar.getInstance();
					DateFormat dayFormat = new SimpleDateFormat("d");
					CurrentDay = dayFormat.format(cal.getTime());
				}
				String CurrentDayXpath = "//td/a[text()='" + CurrentDay + "']";
				Thread.sleep(1000);
				


				fngClick_NOR_withoutWait(CurrentDayXpath);
				Thread.sleep(500);

			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				throw new SkipException(W.getMessage());
			} catch (Throwable t) {
				fnaTake_Screen_Shot("DatePickFail");
				isTestCasePass = false;
				fnpMymsg("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>" + t.getMessage());
				throw new Exception("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>" + t.getMessage());
			}
		}
		/****
		 * Function clicking of an object (button etc) which is NOT present in OR
		 * without handling loading Wait
		 ****/
		public static void fngClick_NOR_withoutWait(String xpath) throws Throwable {
			int retries = 0;
			while (true) {
				try {
					//fnpCheckError("");
					fnpGetORObjectX___NOR(xpath).click();

					break;

				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpClick_NOR function for " + xpath);
						// continue;
					} else {
						throw e;
					}
				} catch (org.openqa.selenium.WebDriverException w) {

					if ((w.getMessage().contains("not clickable at point")) | (w.getMessage().contains("unknown error:"))) {
						if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							retries++;
							Thread.sleep(1000);
							fnpMymsg(retries + "In WebDriverException catch block of fnpClick_NOR function for " + xpath);
							// continue;
						} else {
							throw w;
						}
					} else {
						throw w;
					}
				}
			}

			fnpCheckError("");

		}
		/**** Function clicking of an object (button etc) which is present in OR ****/
		public static void fngClick_OR(String xpathkey) throws Throwable {

			int i = 0;
			while (fngCheckElementPresenceImmediately("BlockedUIScreen")) {
				Thread.sleep(1000);
				i++;
				if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
					break;

				}

			}
			
			fngCheckError("");
			
			fngClick_OR_WithoutWait(xpathkey);
			fngLoading_wait();
			fngIpulseDuringLoading();

		}
		/*********** Wait till main loading icon overs *************/
		public static void fngLoading_wait() throws Throwable {
			int i = 0;


			while (true) {
				if (fngCheckElementDisplayedImmediately("ProgressImageIcon")) {
					fnpMymsg("@@@   main loading is visible - fnpLoading_wait--" + i);

					break;
				} else {


					int maxloopDependOnBrowser = 0;

					if (browserName.equalsIgnoreCase("IE")) {
						// maxloopDependOnBrowser=6;
						// maxloopDependOnBrowser = 4;
						maxloopDependOnBrowser = Integer.parseInt(CONFIG.getProperty("LoadingIconMaxWait"));
						// maxloopDependOnBrowser=Integer.parseInt(CONFIG.getProperty("LoadingIconMaxWait"))+4;
					} else {
						// maxloopDependOnBrowser=3;
						// maxloopDependOnBrowser = 1;
						maxloopDependOnBrowser = Integer.parseInt(CONFIG.getProperty("LoadingIconMaxWait"));
					}

					if (i > maxloopDependOnBrowser) {

						fnpMymsg("@@@    main loading icon not visible after " + (maxloopDependOnBrowser + 1) + " seconds");
						break;
					} else {
						fnpMymsg("@@@    waiting for main  loading visible inside - fnpLoading_wait--" + i);
					}

					Thread.sleep(1000);
					i++;
				}
			}

			i = 0;
			while (fngCheckElementPresenceImmediately("BlockedUIScreen")) {
				Thread.sleep(1000);
				i++;
				fnpMymsg("@  ------fngCheckElementPresenceImmediately 'BlockedUIScreen'------" + i);
				//int maxT=Integer.parseInt(CONFIG.getProperty("loading_Max_waitTime")) ;
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}
				

			}

			i = 0;
			while (fngCheckElementDisplayedImmediately("ProgressImageIcon")) {
				Thread.sleep(1000);
				i++;
				fnpMymsg("@  ------fngCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
				fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}
				

			}

			i = 0;
			while (fngCheckElementDisplayedImmediately("LoadingImg")) {
				fnpMymsg("@@@   while loop when loading is visible now inside - fngLoading_waitInsideDialogBox--" + i);
				Thread.sleep(1000);
				i++;
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
					//break;
				}
				

			}

			fnpCheckError(" after loading ");

		}
		public static void fngIpulseDuringLoading() throws Throwable {

			int i = 0;
			while (fngCheckElementDisplayedImmediately("ProgressImageIcon")) {
				Thread.sleep(1000);
				i++;
				fnpMymsg("@  ------fngCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
				fnpMymsg("@@@    loading is visible ---" + i);
				if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
					break;

				}

			}

		}
		/***********
		 * Function to find and return the object using Xpath which are present in
		 * OR
		 *************/
		public static WebElement fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(String Xpath, String nameOfField) throws Exception {
			int retries = 0;
			WebElement requiredObj = null;
			try {

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				if (driver.findElements(By.xpath(Xpath)).size() > 0) {

					List<WebElement> objList = fnpGetORObject_list_NOR(Xpath, 2);

					for (int i = 0; i < objList.size(); i++) {
						if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

							requiredObj = objList.get(i);
							break;
						}
					}

				} else {
					throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
					// continue;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
					// continue;
				} else {
					throw is;
				}
			}

			catch (Throwable t) {

				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + nameOfField + "] ,plz see screenshot [NotPresent" + nameOfField + SShots + "]");
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

				return requiredObj;
			}

		}
		public static List<WebElement> fngGetORObject_list_NOR(String Xpath, int maxTimeInSeconds) throws Exception {

			int retries = 0;
			while (true) {
				try {

					driver.manage().timeouts().implicitlyWait(maxTimeInSeconds, TimeUnit.SECONDS);
					return driver.findElements(By.xpath(Xpath));

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < maxTimeInSeconds) {
						Thread.sleep(1000);
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
						// continue;
					} else {
						throw s;
					}
				}

				catch (org.openqa.selenium.InvalidSelectorException is) {
					if (retries < 10) {
						Thread.sleep(1000);
						retries++;
						fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
						// continue;
					} else {
						throw is;
					}
				} catch (Throwable t) {
					if (retries < maxTimeInSeconds) {
						// Thread.sleep(1000);
						retries++;
						fnpMymsg(retries + "In Throwable catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);

					} else {
						fnaTake_Screen_Shot("NotPresent" + Xpath);
						throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");
					}
				}

				finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}

		}
		/*********** To check element is displayed immediately or not *************/
		public static boolean fngCheckElementDisplayed_NOR(String Xpath, int maxtimeInSeconds) {

			int i = 0;
			try {

				driver.manage().timeouts().implicitlyWait(maxtimeInSeconds, TimeUnit.SECONDS);
				List<WebElement> elementList = fngGetORObject_list_NOR(Xpath, maxtimeInSeconds);

				for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

					WebElement webElement = (WebElement) iterator.next();
					if (webElement.isDisplayed()) {
						// return true;
						i = 1;
						break;
					} else {
						// return false;
					}

				}

			} catch (Throwable t) {
				// return false;

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

			if (i == 0) {
				return false;

			} else {
				return true;
			}

		}
		/*********** To check element is displayed immediately or not *************/
		public static boolean fngCheckElementDisplayedImmediately_NOR(String Xpath) {

			int i = 0;
			try {

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				List<WebElement> elementList = fngGetORObject_list_NOR(Xpath, 0, 1);

				for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

					WebElement webElement = (WebElement) iterator.next();
					if (webElement.isDisplayed()) {
						// return true;
						i = 1;
						break;
					} else {
						// return false;
					}

				}

			} catch (Throwable t) {
				// return false;

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}

			if (i == 0) {
				return false;

			} else {
				return true;
			}

		}
		public static List<WebElement> fngGetORObject_list_NOR(String Xpath, int maxTimeInSeconds, long maxTimeInMilliSeconds) throws Throwable {

			int retries = 0;
			while (true) {
				try {

					if ((Xpath.trim().toLowerCase().contains("error")) | (Xpath.trim().toLowerCase().contains("ProgressImageIcon"))
							| (Xpath.trim().toLowerCase().contains("BlockedUIScreen"))) {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

					} else {
						driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

					}

					return driver.findElements(By.xpath(Xpath));

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						// if (retries < 3) {
						Thread.sleep(1000);
						retries++;
						fnpMymsg(retries + "In staleElementException catch block of fnpGetORObject_list 3 arguments function for " + Xpath);
						// continue;
					} else {
						throw s;
					}
				}

				catch (org.openqa.selenium.InvalidSelectorException is) {

					if (retries < 10) {
						Thread.sleep(2000);

						fnpMymsg(" @  ---In InvalidSelectorException in ip addresss---" + fnpGetIPAddress() + " machine  for retries --" + retries);

						retries++;
						fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 3 arguments  function for " + Xpath);
						// continue;
					} else {

						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("@   ---********Exception thrown in " + fnpGetIPAddress() + " machine************************************************");

						fnpMymsg(is.getMessage());
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("@   ---********************************************************");
						String PageSourceText = driver.getPageSource().toLowerCase();
						fnpMymsg("@   ---********************************************************");

						// fnpMymsg(fnpFormatReplaceSpecailCharacters(PageSourceText));
						fnpMymsg("@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");

						fnpMymsg("@   ---********************************************************");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");

						throw is;
					}
				} catch (Throwable t) {

					//fnpDesireScreenshot("NotPresent" + Xpath);
					fnaTake_Screen_Shot("NotPresent" + Xpath);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");

				}

				finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}

		}
		public static String fngReturnAStringWholeFirstCharacterIsInUpperCase(String s) {
			String newValue = "";
			String lowercaseString = s.toLowerCase();
			String lowercaseArray[] = lowercaseString.split(" ");

			if (lowercaseArray.length > 1) {
				for (int i = 0; i < lowercaseArray.length; i++) {
					if (lowercaseArray[i].equalsIgnoreCase(" ") | lowercaseArray[i].equalsIgnoreCase("")) {
						newValue = newValue + " " + lowercaseArray[i];
					} else {
						String new_value_firstCharacter = lowercaseArray[i].substring(0, 1);
						String new_value_firstCharacterUpperCase = new_value_firstCharacter.toUpperCase();
						lowercaseArray[i] = lowercaseArray[i].replaceFirst(new_value_firstCharacter, new_value_firstCharacterUpperCase);
						newValue = newValue + " " + lowercaseArray[i];
					}

				}

				newValue = newValue.trim();
		

			} else {
				String new_value_firstCharacter = lowercaseString.substring(0, 1);
				String new_value_firstCharacterUpperCase = new_value_firstCharacter.toUpperCase();
				newValue = lowercaseString.replaceFirst(new_value_firstCharacter, new_value_firstCharacterUpperCase);
			}

			return newValue;
		}
		
		
		public static void fnaSelectDateFromCalenderInSam_old(Integer days) throws Throwable {
		
			
		if(fngCheckElementDisplayedImmediately("Scheduling_startOnCal_Xpath")) {
			
			if(days==0) {
			
			fngClick_InSAM_OR("Scheduling_startOnCal_Xpath");
			Thread.sleep(1000);
			
			fngClick_InSAM_OR("SchedulingStartOn_Today_Date_xpath");
			Thread.sleep(1000);
			
			//fngClick_InSAM_OR("SchedulingStartOn_NowTime_xpath");  
			//Thread.sleep(1000);
			
			//fngClick_InSAM_OR("SchedulingStartOn_SetDateAndTimeButton_xpath");
			//Thread.sleep(1000);
		
		}else if(days >= 1 && days <=7) {
			
			String NextdateXpath = "(//td[@aria-disabled='false'])["+ days +" + 1]";
			
			fngClick_InSAM_OR("Scheduling_EndsOnCal_Xpath");
			Thread.sleep(1000);
			
			fngClick_NOR_withoutWait(NextdateXpath);
			Thread.sleep(1000);
			
			//fngClick_InSAM_OR("SchedulingStartOn_NowTime_xpath");  
			//Thread.sleep(1000);
			
			//fngClick_InSAM_OR("SchedulingStartOn_SetDateAndTimeButton_xpath");
			//Thread.sleep(1000);
			
		}else {
			
			throw new Exception("Invalid Days: Days should not be greater than 7.");
		}
		
		}else {
			
			throw new Exception("Calender icon is not gettting display.");
		}
		
		
	}
		
		public static void fnaSelectDateFromCalenderInSam(String Cal_LabelName) throws Throwable{
			try{
				String DateCalButton_Xpath ="(//label[contains(text(), '"+Cal_LabelName+"')]/following::span[@class='k-icon k-i-calendar'])[1]";
				String DateXpath = "(//td[@class='k-calendar-td k-today']/span)[1]";
						
				fnpCheckElementDisplayed_NOR(DateCalButton_Xpath);
				fnpClick_NOR(DateCalButton_Xpath);
				fnsLoading_wait_In_SAM(1);
				fnsLoading_wait_In_SAM(1);
				
				fnpCheckElementDisplayed_NOR(DateXpath);
				fnpClick_NOR(DateXpath);
				fnsLoading_wait_In_SAM(1);
				fnsLoading_wait_In_SAM(1);
			}catch(Throwable t){
				fnpMymsg(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));	
			}
		}
		
		
		
		
		
		
		/*********** Wait till main loading icon overs *************/
		public static void fnaLoading_wait_In_SAM() throws Throwable {
			int i = 0;


			fngCheckError_In_SAM(" ");
			
			int iwhileCounter=0;
			while (true) {
				iwhileCounter++;
				if (fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath1") | fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath2") | fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath3") ) {
					fnpMymsg("@@@   main loading is visible in SAM - fnpLoading_wait_In_SAM--" + i);
					break;
				} else {

					if (iwhileCounter > 2) {
						break;
					}
					
					
					fnpMymsg("@@@    main loading icon is not visible  in SAM after " + (iwhileCounter ) + " seconds");
					Thread.sleep(1000);
					i++;

				}
			}
			i = 0;
			while (fngCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
				fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
				/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
				break;

			}*/
			
			
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

			}


			i = 0;
			while (fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")) {
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
				fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath2'------" + i);
				fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
				/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
				break;

			}*/
			
			
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

			}
			
			while (fngCheckElementDisplayedImmediately("SAM_loadingtxtXpath3")) {
				//Thread.sleep(1000);
				Thread.sleep(500);
				i++;
				fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath3'------" + i);
				fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
				/*			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
				break;

			}*/
			
			
			int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconnds");
				//break;
			}

			}
			
		
			fngCheckError_In_SAM(" after loading ");
			Thread.sleep(2000);

		}
		

		public static void fngSimpleSelectList_usingSelectClass(String ORXpathKey, String expvalue) throws Throwable {

			WebElement element = fngGetORObjectX(ORXpathKey);
			Select se = new Select(element);
			se.selectByVisibleText(expvalue);
		}
}
