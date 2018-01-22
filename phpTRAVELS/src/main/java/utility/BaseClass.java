package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Constantclass;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties OR;
	public static File F1;
	public static FileInputStream F2;
	
	
	public static WebDriver LaunchBrowser(String Browser)
	{
		
		if(System.getProperty("os.name").contains("Window")) {
			if(Browser.equalsIgnoreCase(Constantclass.Firefox)) {
				System.setProperty(Constantclass.Firefoxdriverfixed,Constantclass.FirefoxdriverPath);
				driver = new FirefoxDriver();
				
			}
			else if(Browser.equalsIgnoreCase(Constantclass.Chrome))	
			{
				System.setProperty(Constantclass.chromedriverfixed,Constantclass.chromedriverPath);
				driver = new ChromeDriver();
			}
			else if(Browser.equalsIgnoreCase(Constantclass.IE))	
			{
				System.setProperty(Constantclass.IEdriverfixed,Constantclass.IEdriverPath);
				driver = new InternetExplorerDriver();
			}
		}
		return driver;
	}
	
	public static void Load_Properties() throws IOException
	{
		OR = new Properties();
		F1 = new File(Constantclass.PropertyPath+"Messages.properties");
		F2 = new FileInputStream(F1);
		OR.load(F2);
		F1 = new File(Constantclass.PropertyPath+"OR.properties");
		F2 = new FileInputStream(F1);
		OR.load(F2);
		
	}
	
	public void ActionPerform(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}

	
	public void JavascriptClick(WebElement element)
	{
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].scrollIntoView()", element);
		element.click();
		
	}
	
	public static WebElement getlocator(String Locator1) throws Exception
	{
		Load_Properties();
		String Locator = OR.getProperty(Locator1);
		
		String S[]=Locator.split(":");
		
		String locatorType = S[0];
		String locatorvalue = S[1];
		
		if(locatorType.toLowerCase().equalsIgnoreCase("id"))
			return driver.findElement(By.id(locatorvalue));
		else if(locatorType.toLowerCase().equalsIgnoreCase("name"))
			return driver.findElement(By.name(locatorvalue));
		else if(locatorType.toLowerCase().equalsIgnoreCase("xpath"))
			return driver.findElement(By.xpath(locatorvalue));
		else if(locatorType.toLowerCase().equalsIgnoreCase("css"))
			return driver.findElement(By.cssSelector(locatorvalue));
		else if((locatorType.toLowerCase().equalsIgnoreCase("linktext"))||(locatorType.toLowerCase().equalsIgnoreCase("link")))
		return driver.findElement(By.linkText(locatorvalue));
		else if(locatorType.toLowerCase().equalsIgnoreCase("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorvalue));
		else if((locatorType.toLowerCase().equalsIgnoreCase("tagname"))||(locatorType.toLowerCase().equalsIgnoreCase("tag")))
			return driver.findElement(By.tagName(locatorvalue));
		else if((locatorType.toLowerCase().equalsIgnoreCase("classname"))||(locatorType.toLowerCase().equalsIgnoreCase("class")))
			return driver.findElement(By.className(locatorvalue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
}
	
	public static Object[][] Getvalues(String WorkbookName,String SheetName) throws IOException
	{
		
		String ExcelPath=Constantclass.ExcelPath+WorkbookName;
		return ExcelRead.readvalues(ExcelPath,SheetName);
	}
	public static WebElement WaitExplicit(WebElement element,int Time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Time);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
		
	}
	
	public static String GetExpectedErrorMessage(String Condition) throws IOException
	{
		Load_Properties();
		String ExpectedMessage = OR.getProperty(Condition);
		return ExpectedMessage;
	}
	

	public static String Snapit(String ImageName) throws IOException
	{
	File Src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	String ActualImageName = System.getProperty("user.dir")+"\\src\\screenshots\\"+ImageName+".png";

FileUtils.copyFile(Src, new File(ActualImageName));

	return ActualImageName;
	}
	public static  void Close_Browser()
	 {
		 driver.close();
	 }

public static void main(String args[])	
{
	LaunchBrowser("firefox");
	
}
	
}
