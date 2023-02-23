package ai.avataar.supernovaV3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import ai.avataar.supernovaV3.base.TestBase;

public class OrionEnginePage extends TestBase {

	@FindBy(xpath = "(//*[name()='svg'])[3]")
	private WebElement Configurator;
		
	@FindBy(xpath = "(//div[@class='relative'])[1]")
	private WebElement Requirement_Btn;
		
	@FindBy(xpath = "(//div[@class='relative'])[2]")
	private WebElement Product_Details_Btn;
	    
	@FindBy(xpath = "(//div[@class='relative'])[3]")
	private WebElement Source_Btn;
		
	@FindBy(xpath = "(//div[@class='relative'])[4]")
	private WebElement Feedback_Btn;
		
	@FindBy(xpath = "(//div[@class='relative'])[2]")
	private WebElement Feedback_Btn_ReviewStage;
		
	@FindBy(xpath = "(//div[@class='relative'])[5]")
	private WebElement Setttings_Btn;
		
	@FindBy(xpath="//button[text()='Upload Files']")
	private WebElement UploadBtn;
		
	@FindBy(xpath = "//button[normalize-space()='Submit']")
	private WebElement EngineSubmitBtn;
		
//Engine - Feedback button
	@FindBy(xpath = "//button[@data-for='Feedback-feedback']//*[name()='svg']")
	private WebElement FeedbackBtn;
				
	@FindBy(xpath = "//div[normalize-space()='Reject']")
	private WebElement FeedbackRejectBtn;
				
	@FindBy(xpath = "//div[normalize-space()='Accept']")
	private WebElement FeedbackAcceptBtn;
				
	@FindBy(xpath = "(//*[name()='path'][@fill-rule='evenodd'])[22]")		//updated to [22] on 28-11-2022
	private WebElement FeedbackCloseBtn;							
				
//added newly 
	@FindBy(xpath = "//div[@class='select-dropdown__input-container css-19bb58m']")
	private WebElement SelectReasonsDropDown;
				
	@FindBy(xpath = "//div[@class='absolute top-0 left-0 w-full h-full transition-all z-50']")
	private WebElement UploadImages;
				
	@FindBy(xpath = "//textarea[@placeholder='Add comment']")
	private WebElement AddComments;
				
	@FindBy(xpath = "//button[@class='flex justify-center items-center p-0 w-4 h-4 max-h-4 text-sm font-semibold font-choco bg-transparent']//*[name()='svg']//*[name()='path' and contains(@fill-rule,'evenodd')]")
	private WebElement CloseSelectedReason;
				
	@FindBy(xpath = "//button[@icon='ChevronLeft']")
	private WebElement backButton;
	
	//Initializing page objects:
		public OrionEnginePage() { //constructor
			PageFactory.initElements(driver, this);//this means current class variables/objects
		}
	
//============================Action Methods for Place order page====================================//
	
		public void click_Requirement_Btn() {
			Requirement_Btn.click();
		}
		
		public void click_Product_Details_Btn() {
			Product_Details_Btn.click();
		}
		
		public void click_Source_Btn() {
			Source_Btn.click();
		}
		
		public void click_Feedback_Btn() {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			    .withTimeout(Duration.ofMillis(15000))
			    .pollingEvery(Duration.ofMillis(1000))
			    .ignoring(Exception.class);
			    wait.until(ExpectedConditions.elementToBeClickable(Feedback_Btn));
			Feedback_Btn.click();
		}
		
		public void click_Feedback_Btn_ReviewStage() {
			Feedback_Btn_ReviewStage.click();
		}
		
		public void click_Setttings_Btn() {
			Source_Btn.click();
		}
		
		public void click_Configurator_Btn() {
			Configurator.click();
		}
		
		public void click_UploadFiles(String UploadDialogBoxNo) {
			WebElement fileUpload = driver.findElement(By.xpath("(//button[@icon='UploadIcon'])["+UploadDialogBoxNo+"]"));
			fileUpload.click();
		}
		
		public void click_Upload_Btn() {
			UploadBtn.click();
		}
		
		public void click_EngineSubmitBtn() {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		    	.withTimeout(Duration.ofMillis(25000))
		    	.pollingEvery(Duration.ofMillis(1000))
		    	.ignoring(Exception.class);
		    	wait.until(ExpectedConditions.elementToBeClickable(EngineSubmitBtn));
		
		    	EngineSubmitBtn.click();
		}
	
		public void click_FeedbackBtn() {
			FeedbackBtn.click();
		}	
		
		public void click_FeedbackRejectBtn() {
			FeedbackRejectBtn.click();
		}	
		
		public void click_FeedbackAcceptBtn() {
			FeedbackAcceptBtn.click();
		}	
				
		public void click_FeedbackCloseBtn() {
			FeedbackCloseBtn.click();
		}	
	
		public void click_SelectRejectionReasonsDropdown() {
			SelectReasonsDropDown.click();
		}
	
		public void select_Reasons(String issueType) throws InterruptedException {
			WebElement issue = driver.findElement(By.xpath("//div[text()='"+issueType+"']"));
			issue.click();
		}

		public void click_uploadImagesAfterRejection() {
			UploadImages.click();
		}

		public void click_addComments() {
			AddComments.click();
		}
		
		public void enter_RejectionCommentsText() {
			AddComments.sendKeys("Rejected - due to some issue");
		}
		
		public void click_closeCloseSelectedReason() {
			CloseSelectedReason.click();
		}
		
		public void click_BackBtn_fromEngine() {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		    	.withTimeout(Duration.ofMillis(6000))
		    	.pollingEvery(Duration.ofMillis(1500))
		    	.ignoring(Exception.class);
		    	wait.until(ExpectedConditions.visibilityOf(backButton));	
			backButton.click();
		}
		
}
