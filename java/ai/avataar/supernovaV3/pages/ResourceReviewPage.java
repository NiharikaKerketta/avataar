package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class ResourceReviewPage extends TestBase {

//	@FindBy(xpath= "(//*[name()='svg'][@stroke='currentColor'])[28]")
	@FindBy(xpath= "//div[@class='switch-btn tick']")  //New xpath added because blocked by Accept
//	@FindBy(xpath= "(//*[name()='path'])[65]")
	private WebElement AcceptIcon;
	
	
	
	@FindBy(xpath= "(//div[@class='switch-btn cross'])[1]")   //New xpath added because blocked by Reject
//	@FindBy(xpath= "(//*[name()='path'])[66]")
	private WebElement RejectIcon;
	
	@FindBy(xpath = "(//div[@class='CustomFormFields_selectContainer__uYf1X']//div)[2]")
	private WebElement ComplexityDropdown;
	
	@FindBy(xpath = "(//div[@class='add-category-icon'])[1]")
	private WebElement CategoryBtn;
	  
	@FindBy(xpath = "(//input[@placeholder='Search Category'])[1]")
	private WebElement SearchCategoryBtn;
	
	//Add Category-Main Popup
	public void select_category(String category) {
		  WebElement cat = driver.findElement(By.xpath("//span[contains(text(),'"+category+"')]"));
		  cat.click();
	 } 

	//Add Category-Sub 
	public void select_subcategory(String subcategory) {
		  WebElement subcat = driver.findElement(By.xpath("//span[contains(text(),'"+subcategory+"')]"));
		  subcat.click();
	 }
	 
	 @FindBy(xpath = "//span[normalize-space()='Save']")
	 private WebElement AddCategorySaveBtn;
	
	
	

	 
	 
	 
	 
	 
	 
	
	//Initializing page objects:
			public ResourceReviewPage() { //constructor
				PageFactory.initElements(driver, this);//this means current class variables/objects
			}
		
	//============================Action Methods for Resource Review page====================================//
	
			public void click_AcceptIcon() {
				AcceptIcon.click();
			}
			
			public void click_RejectIcon() {
				RejectIcon.click();
			}
	
			public void click_ComplexityDropdown() {
				ComplexityDropdown.click();
			}
			
			//Complexity Method
			public void select_complexity(String complexity) {
				 WebElement complex = driver.findElement(By.xpath("(//div[@class='CustomFormFields_optionItem__i+8nG'])["+complexity+"]"));
				 complex.click();
			 }
			
			public void click_CategoryBtn() {
				CategoryBtn.click();
			}
			
			public void click_SaveBtn_category() {
				AddCategorySaveBtn.click();
			}
	
	
	
	
}
