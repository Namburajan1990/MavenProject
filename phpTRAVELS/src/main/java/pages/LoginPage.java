package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import configuration.Constantclass;
import utility.BaseClass;

public class LoginPage extends BaseClass {
	
	 @FindBy(how=How.NAME,using="username")
	 private WebElement txtbx_Email;
	 @FindBy(how=How.NAME,using="password")
	 private WebElement txtbx_Password;
	 @FindBy(how=How.XPATH,using="//button[@class='btn btn-action btn-lg btn-block loginbtn']")
	 private WebElement Btn_Login;
	 @FindBy(how=How.ID,using="remember-me")
	 private WebElement Rememeber_Me;
	 @FindBy(how=How.XPATH,using="//div[@class='col-md-2 go-right']//img[@class='img-responsive go-right img-thumbnail']")
	 private WebElement LoginConfirm;
	 @FindBy(how=How.XPATH,using="(//a[contains(@href,'//www.phptravels.net/register')])[2]")
	 private WebElement SignUp;
	 @FindBy(how=How.XPATH,using="(//li[@id='li_myaccount']/a[@class='dropdown-toggle go-text-right'])[2]")
	 private WebElement Btn_MyAccount;
	 @FindBy(how=How.XPATH,using="(//a[contains(@href,'//www.phptravels.net/login')])[2]")
	 
	 private WebElement Btn_LoginHome;
	 @FindBy(how=How.NAME,using="firstname")
	 private WebElement txtbox_FirstName;
	 @FindBy(how=How.NAME,using="lastname")
	 private WebElement txtbox_LastName;
	 @FindBy(how=How.NAME,using="phone")
	 private WebElement txtbox_Phone;
	 @FindBy(how=How.NAME,using="email")
	 private WebElement txtbox_EmailSign;
	 @FindBy(how=How.NAME,using="password")
	 private WebElement txtbox_Password;
	 @FindBy(how=How.NAME,using="confirmpassword")
	 private WebElement txtbox_ConfirmPassword;
	 @FindBy(how=How.XPATH,using="//button[@class='signupbtn btn_full btn btn-action btn-block btn-lg']")
	 private WebElement Btn_Signup;
	 @FindBy(how=How.XPATH,using =  "(//i[@class='icon-money-2 go-right'])[2]")
	 private WebElement CurrencyHYperlink;
	 
	 LoginPage()
	 {
		 this.driver = driver; 
		 PageFactory.initElements(driver, this);
	 }
	 
	 public static void LaunchURL(String URl,String Browser)
	 {
		 BaseClass.LaunchBrowser(Constantclass.Chrome);
		 driver.get(URl);
	 }
	 
	 
	 public  void LoginSuccesfull(String UserName,String Password)
	 {
		 BaseClass.WaitExplicit(Btn_MyAccount, 30);		 
		 Btn_MyAccount.sendKeys(Keys.ENTER);	
		 Btn_LoginHome.sendKeys(Keys.ENTER);
		 BaseClass.WaitExplicit(txtbx_Email, 30);
		 txtbx_Email.sendKeys(UserName);
		 txtbx_Password.sendKeys(Password);
		 BaseClass.WaitExplicit(Btn_Login, 10);
		 Btn_Login.sendKeys(Keys.ENTER);
		 BaseClass.WaitExplicit(LoginConfirm,30);
		 Assert.assertTrue(LoginConfirm.isDisplayed());
	 }
	 
	 public void Register(String FirstName,String LastName,String MobileNumber,String Email,String Password,String ConfirmPassword)
	 {
		 BaseClass.WaitExplicit(Btn_MyAccount, 30);		 
		 Btn_MyAccount.sendKeys(Keys.ENTER);	
		 BaseClass.WaitExplicit(SignUp, 30);	
		 SignUp.sendKeys(Keys.ENTER);
		 BaseClass.WaitExplicit(txtbox_FirstName, 30);
		 txtbox_FirstName.sendKeys(FirstName);
		 txtbox_LastName.sendKeys(LastName);
		 txtbox_Phone.sendKeys(MobileNumber);
		 txtbox_EmailSign.sendKeys(Email);
		 txtbox_Password.sendKeys(Password);
		 txtbox_ConfirmPassword.sendKeys(ConfirmPassword);
		 BaseClass.WaitExplicit(Btn_Signup, 30);
		 Btn_Signup.sendKeys(Keys.ENTER);
		 BaseClass.WaitExplicit(LoginConfirm,30);
		 Assert.assertTrue(LoginConfirm.isDisplayed());
		 
	 }
	
	 public void selectValue(String Currency)
	 {
		 ActionPerform(CurrencyHYperlink);
		 WebElement element = driver.findElement(By.xpath(( "(//a[@class='go-text-right' and contains(text(),'"+Currency+"')])[2]")));
		JavascriptClick(element);
	 }
	 
	 
	 @DataProvider(name="Register")
	 public static Object[][] credentials() throws IOException {
		 
	        return BaseClass.Getvalues("Datadriver.xlsx","Registeration");
	 
	  }
	 
	@Test(enabled=false)
	public void LoginVerfiy() {
		// TODO Auto-generated method stub
		LaunchURL("http://www.phptravels.net","chrome");
		LoginPage login = new LoginPage();
		login.selectValue("INR");
	}
	@Test(dataProvider="Register")
	public void RegisterSuccesfull(String FirstName,String LastName,String MobileNumber,String Email,String Password,String ConfirmPassword) {
		// TODO Auto-generated method stub
		LaunchURL("https://www.phptravels.net/","chrome");
		LoginPage login = new LoginPage();
		login.Register(FirstName, LastName, MobileNumber, Email, Password, ConfirmPassword);
		BaseClass.Close_Browser();
	}
}
