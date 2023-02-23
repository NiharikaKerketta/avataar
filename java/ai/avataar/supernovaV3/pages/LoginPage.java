package ai.avataar.supernovaV3.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class LoginPage extends TestBase {
	
//-------Page Factory - Object repository----------//
	
	@FindBy(xpath="//button[text()='Sign In']")
	WebElement signInBtn;
	
	@FindBy (id="identifierId")
	WebElement username;
	
	@FindBy(xpath="//button[text()='Continue']")
	WebElement continueBtn;
	
	@FindBy (name="password")
	WebElement password;

	@FindBy (name="email")
	WebElement email;
	
	//Initializing page objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}
	
//-----------Action methods------------------------//
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public CommonUtils login(String un,String pwd) throws InterruptedException { //return type is commonUtils class
		email.sendKeys(un);
		continueBtn.click();
		password.sendKeys(pwd);
		signInBtn.click();
		Thread.sleep(5000);
		return new CommonUtils();
	}

}
