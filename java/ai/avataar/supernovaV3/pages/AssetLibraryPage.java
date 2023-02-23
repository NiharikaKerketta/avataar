package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class AssetLibraryPage extends TestBase {
	
	Actions action = new Actions(driver);

	@FindBy(xpath = "(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]")
	public WebElement searchBar;
	
	@FindBy(xpath = "(//p[@class='AssetTile_name__GgKdC'])[1]")
	public WebElement asset;
	
	@FindBy(xpath = "//span[text()='View All Images']")
	public WebElement viewAllImages;
	
	@FindBy(xpath = "//div[@class='ImageCarousel_img-nav-btn__kXH5o ImageCarousel_next-btn__djt1r']")
	public WebElement imageCarousel;
	
	@FindBy(xpath = "//span[text()='Download All Files']")
	public WebElement downloadAllFiles;
	
	@FindBy(xpath = "//div[@class='file-container']")
	public WebElement downloadOneFile;
	
	@FindBy(xpath = "//div[@class='AssetsLibrary_back-button__E2D4B']")
	public WebElement backButton;
	
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[1]")
	public WebElement dropdown3D;
	
	@FindBy(xpath = "//span[text()='Clear']")
	public WebElement clearBtn;
	
	@FindBy(xpath = "(//span[@class='MuiIconButton-label']//input)[2]")
	public WebElement checkbox2;
	
	@FindBy(xpath = "(//*[name()='svg'][@class='MuiSvgIcon-root'])[2]")
	public WebElement searchByImage;
	
	@FindBy(xpath = "//div[@class='AssetsLibrary_file-upload__-oprH']")
	public WebElement uploadFile;
	
	@FindBy(xpath = "//span[text()='Search']")
	public WebElement searchBtn;
	
	@FindBy(xpath = "(//*[name()='svg'][@class='MuiSvgIcon-root'])[2]")
	public WebElement closeBtn;
	
	//Initializing page objects:
			public AssetLibraryPage() {
				PageFactory.initElements(driver, this);//this means current class variables/objects
			}
			
	public void click_search(String searchTxt) throws InterruptedException { //enter what you want to search
		searchBar.sendKeys(searchTxt);
		searchBar.click();
	}
	
	public void click_asset() {
		asset.click();
	}
	
	public void click_ViewAllImages() {
		viewAllImages.click();
	}
	
	public void click_ImageSlider() {
		imageCarousel.click();
	}
	
	public void click_DownloadAllFiles() {
		downloadAllFiles.click();
	}
	
	public void click_DownloadOneFile() {
		downloadOneFile.click();
	}
	
	public void click_BackButtonIn3DMesh() {
		backButton.click();
	}
	
	public void click_3Ddropdown() {
		dropdown3D.click();
	}
	
	public void click_checkbox(String checkboxNo) {
		 WebElement no = driver.findElement(By.xpath("//input[@type='checkbox']["+checkboxNo+"]"));
		 no.click();
	}
	
	public void click_AnyCheckbox(String checkboxNo) {
		 WebElement no = driver.findElement(By.xpath("//span[@class='MuiIconButton-label']//input)["+checkboxNo+"]"));
		 no.click();
	}
	
	public void click_clearBtn() {
		clearBtn.click();
	}
	
	public void click_checkbox2() {
		checkbox2.click();
	}
	
	public void click_searchByImage() {
		searchByImage.click();
	}
	
	public void click_clickUploadImage() {
		uploadFile.click();
	}
	
	public void click_SearchBtn() {
		searchBtn.click();
	}
	
	public void click_CloseBtn() {
		closeBtn.click();
	}
}
