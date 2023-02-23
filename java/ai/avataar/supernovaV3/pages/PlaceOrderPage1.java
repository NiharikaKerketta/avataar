package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class PlaceOrderPage1 extends TestBase{
	
	@FindBy(xpath= "//span[text()='Order from Avataar']")
	private WebElement PlaceOrderWithAvataar_Btn;
	
	@FindBy(xpath= "//div[text()='Order from Avataar']")
	private WebElement PlaceOrderWithAvataar_Down_Btn;
	
	@FindBy(xpath = "//div[text()='Upload 3D models']")
	private WebElement BYO3D_Down_Btn;
	
	@FindBy(xpath = "//span[text()='Upload 3D models']")
	private WebElement BYO3D_Up_Btn;
	
	//Initializing page objects:
		public PlaceOrderPage1() { //constructor
			PageFactory.initElements(driver, this);//this means current class variables/objects
		}
	
	//Class from Manju
	public String actualBottom_Text;
	
	@FindBy(xpath="//div[@class='order-alt-button-wrapper']//div[1]")
	private WebElement Bottom_hyperlink;
	
	@FindBy(xpath= "//div[text()='Order from Avataar']")
	private WebElement OrderFromAvataar_Text;
	
	@FindBy(xpath= "//span[text()='Order from Avataar']")
	private WebElement OrderFromAvataar_Btn;
	
	@FindBy(xpath = "//div[text()='Upload 3D models']")
	private WebElement BYO3D_text;
	
	@FindBy(xpath="//span[text()='Upload 3D models']")
	private WebElement BYO3D_Btn;
	
	
	//------------------------------Action Methods---------------------------------------------------
	
	public String getBottomOrderText() {
		actualBottom_Text = Bottom_hyperlink.getText();
		//System.out.println("switch order bottom text is "+ actualBottom_Text);
		return actualBottom_Text;
	}
	
	public void click_OrderFromAvataar_Btn() {
		if(actualBottom_Text.equals("Upload 3D models")) {
			OrderFromAvataar_Btn.click();
		}
		else {
			OrderFromAvataar_Text.click();
			OrderFromAvataar_Btn.click();
		}
	}
	
	public void click_Upload3DModels_Btn () {
		if(actualBottom_Text.equals("Order from Avataar")) {
			BYO3D_Btn.click();
		}
		else {
			BYO3D_text.click();
			BYO3D_Btn.click();
		}
	}
	
	
}
