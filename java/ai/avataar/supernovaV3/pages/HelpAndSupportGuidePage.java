package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class HelpAndSupportGuidePage extends TestBase {

	@FindBy(xpath="//span[text()='Continue']")
	public WebElement ContinueBtn;
	
	@FindBy(xpath="//span[text()='Next']")
	private WebElement NextBtn;
	
	@FindBy(xpath="//span[text()='Back']")
	private WebElement BackBtn;
	
	@FindBy(xpath="//span[@class='MuiIconButton-label']")
	private WebElement CloseBtn;
	
	@FindBy(xpath="//span[text()='Finish']")
	private WebElement FinishBtn;
	
	//Get text elements
	@FindBy(xpath="//div[text()='Step 1: Navigate to the menu bar on the left']")
	private WebElement step1;
	
	@FindBy(xpath="//div[text()='Step 2: Select Place Order from the menu']")
	private WebElement step2;
	
	@FindBy(xpath="//div[text()='Step 3: Select products']")
	private WebElement step3;
	
	@FindBy(xpath="//div[text()='Step 4: Place Order']")
	private WebElement step4;
	
	@FindBy(xpath="//div[text()='Step 5: Review']")
	private WebElement step5;
	
	@FindBy(xpath="//div[text()='Step 6: View Experience']")
	private WebElement step6;
	
	@FindBy(xpath="//div[text()='Step 7: Accept/Reject']")
	private WebElement step7;
	
	@FindBy(xpath="//div[text()='Step 8: Navigate to Publish in the menu']")
	private WebElement step8;
	
	@FindBy(xpath="//div[text()='Step 9: Publish']")
	private WebElement step9;
	
	@FindBy(xpath="//div[text()='Step 10: Navigate to Live in the menu']")
	private WebElement step10;
	
	@FindBy(xpath="//div[text()='Step 11: Live ']")
	private WebElement step11;
	
	@FindBy(xpath="//div[text()='Step 12: Analytics ']")
	private WebElement step12;
	
	//Initializing page objects:
	public HelpAndSupportGuidePage() {
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}
	
	public void click_NextBtn() {
		NextBtn.click();
	}
	
	public void click_BackBtn() {
		BackBtn.click();
	}
	
	public void click_CloseBtn() {
		CloseBtn.click();
	}
	
	public void click_FinishBtn() {
		FinishBtn.click();
	}
	
	public void click_ContinueBtn() {
		ContinueBtn.click();
	}
	
	public void click_Help_Btn() {
		Help_Btn.click();
	}
	//generic method
	
	public void choose_OptionFrom_HelpAndSupport(String option) throws InterruptedException {
		WebElement click_option = driver.findElement(By.xpath("//div[text()='"+option+"']"));
		click_option.click();
		Thread.sleep(3000);
	}		
	
	//get text methods 
	public String getStep1Text() {
		return step1.getText();
	}
	
	public String getStep2Text() {
		return step2.getText();
	}
	
	public String getStep3Text() {
		return step3.getText();
	}
	
	public String getStep4Text() {
		return step4.getText();
	}

	public String getStep5Text() {
		return step5.getText();
	}
	
	public String getStep6Text() {
		return step6.getText();
	}
	
	public String getStep7Text() {
		return step7.getText();
	}
	
	public String getStep8Text() {
		return step8.getText();
	}
	
	public String getStep9Text() {
		return step9.getText();
	}
	
	public String getStep10Text() {
		return step10.getText();
	}
	
	public String getStep11Text() {
		return step11.getText();
	}
	
	public String getStep12Text() {
		return step12.getText();
	}
  	@FindBy(xpath = "//div[text()='Help']")
   	private WebElement Help_Btn;
}
