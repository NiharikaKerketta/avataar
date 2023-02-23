package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class LeadFlowPage extends TestBase {
	
	
	//--------------------------Artist Assignment----------------------------//
	
		@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[14]")
		private WebElement ArtistDropdown;
		
		 public LeadFlowPage(WebDriver driver) {
			 PageFactory.initElements(driver, this);
		 }

		public void SelectModellingArtist(String modelling_artist) {
			WebElement ma = driver.findElement(By.xpath("//span[normalize-space()='"+modelling_artist+"']"));
			ma.click();
		}
	
		//---------------------------Modelling QC----------------------------//
		
			//Engine - Feedback button
			@FindBy(xpath = "//button[@data-for='Feedback-feedback']//*[name()='svg']")
			private WebElement FeedbackBtn;
			
			@FindBy(xpath = "//div[normalize-space()='Reject']")
			private WebElement FeedbackRejectBtn;
			
			@FindBy(xpath = "//div[normalize-space()='Accept']")
			private WebElement FeedbackAcceptBtn;
			
			@FindBy(xpath = "(//*[name()='path'][@fill-rule='evenodd'])[21]")
			private WebElement FeedbackCloseBtn;
			
			//Engine Submit Button - Utility
			@FindBy(xpath = "//button[normalize-space()='Submit']")
			private WebElement EngineSubmitBtn;
			
			@FindBy(xpath = "(//*[name()='svg'])[19]")
			private WebElement RejectedReasonDd;
			
			//Engine Back Button - Uitility class
			@FindBy(xpath = "//button[@icon='ChevronLeft']")
			private WebElement EngineBackBtn;
			
			@FindBy(xpath = "//textarea[@placeholder='Add comment']")
			private WebElement AddCommentSection;
			
			//Download Avataar App - Can be used with Assertion if needed - Utility
			@FindBy(xpath = "//button[@aria-label='close']")
			private WebElement DownloadAvataar3DAppCloseBtn;
			
			
			//Action Methods
			public void click_ModellingArtistDropdown() {
				ArtistDropdown.click();
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
					
			public void click_EngineSubmitBtn() {
				EngineSubmitBtn.click();
			}	
			
			public void click_RejectedReasonDd() {
				RejectedReasonDd.click();
			}	
			
			public void click_EngineBackBtn() {
				EngineBackBtn.click();
			}	
			
			public void click_AddCommentSection() {
				AddCommentSection.click();
			}	
			
			public void click_DownloadAvataar3DAppCloseBtn() {
				DownloadAvataar3DAppCloseBtn.click();
			}	
			
			//xpath for Uploading Images while Rejecting is remaining

			
			
			//Add Comment Method
			public void addComment(String Comments) {
				AddCommentSection.sendKeys("+Comments+");
				
			
}
}
