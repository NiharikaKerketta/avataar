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

public class BYO3DPage extends TestBase {

		@FindBy(xpath = "//span[text()='Upload Your 3D Models']")
		private WebElement BYO3D_Btn;
		
		//Bring YO3D flow - Upload Button
		@FindBy(xpath = "//span[text()='Upload']")
		private WebElement Upload3DModel_Btn;
		
		@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[22]")
		private WebElement Assign3DTeamDropdown;
		
		@FindBy(xpath = "(//div[@class='column-text'])[2]")
		private WebElement productStatusAtBringYO3D;
		
		@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[11]")
		private WebElement Role3DDropdown;
		
		//----------------------------------------------------------------
		
		@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[15]")
		private WebElement Atrist3DDropdown;
		
		@FindBy(xpath = "(//div[@class='column-text'])[3]")
		private WebElement productStatusAtBYO3D;
		
		@FindBy(xpath = "(//input[@type='checkbox'])[2]")
		private WebElement SingleCheckbox;
		
		@FindBy(xpath = "//button[@icon='ChevronLeft']")
		private WebElement backButton;
		
		@FindBy(xpath = "(//*[name()='svg'][@class='MuiSvgIcon-root'])[1]")
		private WebElement closePopup;
		
		@FindBy(xpath = "(//div[@class='relative'])[2]")
		private WebElement Review_Stage_Feedback_Btn;
		
		//Initializing page objects:
				public BYO3DPage() { //constructor
					PageFactory.initElements(driver, this);//this means current class variables/objects
				}
	
		//----------------Methods-------------------//
		
		public void click_UploadYour3DModels_Btn() {
			BYO3D_Btn.click();
		}

		public void click_Upload3DModel_Btn() {
			Upload3DModel_Btn.click();
		}
		
		public void click_Assign3DTeamDropdown() {
			Assign3DTeamDropdown.click();
		}
		
		public void click_3DRoleDropdown() {
			Role3DDropdown.click();
		}
		
		public void click_ReviwStage_Feedback_Btn() {
			Review_Stage_Feedback_Btn.click();
		}
		
		//Send the name of 3D Team in the dropdown to select the Team
		public void select_3DTeam(String TeamName) {
			WebElement Assign3DTeam = driver.findElement(By.xpath("//span[text()='"+TeamName+"']"));
			Assign3DTeam.click();
		}
		
		// In Publish stage we are reading the status of processed product as "Processing" or "Ready for publish"
		//This status can be used for polling in Auto Processing
		public String get_StatusOfProductAtBringYO3D() {
			String statusOfProdAtBringYO3D = productStatusAtBringYO3D.getText();
			return statusOfProdAtBringYO3D;
		}
		// After this Same flow will continue
		
		//--------------------------------------------------------------------------
		
		public void click_Atrist3DDropdown() {
			Atrist3DDropdown.click();
		}
		
		public void select_3DArtist(String ThreeDArtist) {
			WebElement artist = driver.findElement(By.xpath("//span[normalize-space()='"+ThreeDArtist+"']"));
			artist.click();
			}
		
		public String get_StatusOfProductAtBYO3D() {
			String statusOfProdAtBYO3D = productStatusAtBYO3D.getText();
			return statusOfProdAtBYO3D;
		}

		public void check_product_AutoProcess_status_and_ClickCheckBox() {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		    		.withTimeout(Duration.ofSeconds(300))
		    		.pollingEvery(Duration.ofSeconds(3))
		    		.ignoring(Exception.class);
		    		
		    		wait.until(ExpectedConditions.elementToBeClickable(SingleCheckbox));
		    		driver.navigate().refresh();
		    		SingleCheckbox.click();
		}	
		
		public void click_BackButton_fromEngine() {
			backButton.click();
		}
		
		public void click_ClosePopupAtPublishStage() {
			closePopup.click();
		}
}
