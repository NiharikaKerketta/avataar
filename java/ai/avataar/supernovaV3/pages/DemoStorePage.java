package ai.avataar.supernovaV3.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ai.avataar.supernovaV3.base.TestBase;
public class DemoStorePage extends TestBase{

	Actions actions = new Actions(driver);
	
	//Page factory - objects repository
	@FindBy(xpath= "//button[contains(@class,'CustomButton_button__a4DjW CustomButton_contained__LNKWI')]")
	public WebElement Continue_Btn_WelcomePage;
	
	@FindBy(xpath= "(//span[@class='MuiButton-label'])[1]")
	private WebElement OrderFromAvataar_Btn;
	
	@FindBy(xpath= "(//input[@data-indeterminate='false'])[2]")
	private WebElement Checkbox_InPublishStage ;
	
	@FindBy(xpath= "//button[@id='tableSubmitAction']//span")
	private WebElement Publish_Btn;
	
	@FindBy(xpath= "//div[text()='In your room']")
	private WebElement InYourRoom_Toggle;

	@FindBy(xpath= "//p[text()='Copy Link']")
	private WebElement CopyLink_Btn;
	
	@FindBy(xpath= "//div[text()='View in 3D']")
	private WebElement ViewIn3D_Toggle;
	
	@FindBy(xpath= "//div[@class='QRPage_scanner__16oZR']/following-sibling::canvas[1]")
	private WebElement QRPageScanner;
	
	@FindBy(xpath= "(//button[@class='Button_button__2Aw9C'])[1]")
	private WebElement Dimension_Icon;
	
	@FindBy(xpath= "(//button[@class='Button_button__2Aw9C'])[2]")
	private WebElement Animation_Icon;
	
	@FindBy(xpath= "//div[@class='MuiDialogTitle-root expPromptHeader']//button[1]")
	private WebElement Close_Icon_ExperiencePage;
	
	@FindBy(xpath= "//div[@id='react-joyride-step-6']/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]")
	private WebElement DemoCloseTitle;
	
	@FindBy(xpath= "//button[text()='Place 3D Order']")
	private WebElement Place3DOrder_Btn;
	
	@FindBy(xpath= "//span[text()='Add to cart']")
	private WebElement AddToCartButton;
	
	//Initializing page objects:
		public DemoStorePage() {
			PageFactory.initElements(driver, this);//this means current class variables/objects
		}
	
	public void Select_checkbox(int Product_RowNumber) {
		WebElement checkbox_demo = driver.findElement(By.xpath("(//input[@type='checkbox'])["+Product_RowNumber+"]"));
		checkbox_demo.click();
	}
	
	public void click_OrderFromAvataar_Btn() {
		WebElement element = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[1]"));
		actions.moveToElement(element).click().build().perform();
	}
	
	//Action Methods
	public void click_Continue_Btn_WelcomePage () {
		Continue_Btn_WelcomePage.click();
	}
	
	public void select_Checkbox_InPublishStage () {
		WebElement element = driver.findElement(By.xpath("(//input[@data-indeterminate='false'])[2]"));
		actions.moveToElement(element).click().build().perform();
	}
	
	public void click_Publish_Btn () {
		WebElement element = driver.findElement(By.xpath("//button[@id='tableSubmitAction']//span"));
		actions.moveToElement(element).click().build().perform();
	}
	
	public void click_InYourRoom_Toggle () {
		
		WebDriverWait wait = new WebDriverWait(driver,(60));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='Button_button__2Aw9C'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='In your room']"))).click();
		
//		WebElement element = driver.findElement(By.xpath("(//button[@class='Button_button__2Aw9C'])[1]"));
//		actions.moveToElement(element).click().build().perform();
	//	actions.moveToElement(AddToCartButton).moveByOffset(-395, -40).click().perform();
	}

	public void click_CopyLink_Btn () {
		CopyLink_Btn.click();
	}
	
	public void click_ViewIn3D_Toggle () {
		ViewIn3D_Toggle.click();
	}

	public void click_Dimension_Icon () {
		Dimension_Icon.click();
	}
	
	public void click_Animation_Icon () {
		Animation_Icon.click();
	}
	
	public void click_Close_Icon_ExperiencePage () {
		Close_Icon_ExperiencePage.click();
	}
	
	public String getDemoCloseTitle () {
		return DemoCloseTitle.getText();
	}
	
	public void click_Place3DOrder_Btn () {
		Place3DOrder_Btn.click();
	}
	
	public void click_AddToCart_Btn() {
		AddToCartButton.click();
	}
	
	
	
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
