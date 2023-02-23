package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

public class Customer_Manager_Rejection_Page extends TestBase {

	@FindBy(xpath = "(//*[name()='svg'][@class='MuiSvgIcon-root'])")
	private WebElement Resource_Review_Rejection_Close_Popup;
	
	@FindBy(xpath = "//textarea[@placeholder='Add Comment for rejections']")
	private WebElement Rejection_comment_area;
	
	@FindBy(xpath = "//span[normalize-space()='Save']")
	private WebElement RRR_save_button;
	
	@FindBy(xpath = "(//div[@class='add-requirement'])[1]")
	private WebElement Add_Requirement_Btn;
	
	@FindBy(xpath = "//textarea[@class='CustomFormFields_textarea__LkFPR']")
	private WebElement Additional_Req_Comment_Box;
	
	@FindBy(xpath = "//span[normalize-space()='Save']")
	private WebElement Add_Additional_Req_Save_Btn;
	
	@FindBy(xpath = "//div[@class='file-upload']")
	private WebElement Files_Browse_Btn;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement Add_Additional_Req_close_Btn;
	
	
	@FindBy(xpath = "//div[contains(@class,'upload-border-image relative')]//div[1]")
	private WebElement L2_File_Upload;
	
	@FindBy(xpath = "//textarea[@placeholder='Add comment']")
	private WebElement Add_L2_Rej_Comments_Btn;
	
	@FindBy(xpath = "//div[normalize-space()='3D Model Rejected']")
	private WebElement L2_Rej_popup_text;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement close_L2_Popup;
	
	
	//Initializing page objects:
	public Customer_Manager_Rejection_Page() { //constructor
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}

	public void click_close_RRR_popup() {
		Resource_Review_Rejection_Close_Popup.click();
	}													//Here RRR - REsource Review Rejection
	
	public void select_Rejection_Reason(String reason) {
		WebElement Rejection_Reason = driver.findElement(By.xpath("//span[contains(text(),'"+reason+"')]"));
		Rejection_Reason.click();
	}
	
	public void click_Add_Rejection_comment_area() {
		Rejection_comment_area.click();
	}
	
	public void add_Rejection_comment_text() {
		Rejection_comment_area.sendKeys("Manager Rejecting for some Reason");
	}
	
	public void click_Rejection_popup_save_Btn() {
		RRR_save_button.click();
	}
	
	public void click_Add_Req_Btn() {
		Add_Requirement_Btn.click();
	}
	
	public void SwitchTo_Add_Additional_Req_Options(int num) {
		driver.findElement(By.xpath("//div[@class='tab-wrapper add-requirements-tabs']//div["+num+"]")).click();
	}
	//Pass int num as 1 for Product Images
	//Pass int num as 2 for Comments
	//Pass int num as 3 for Files
	
	public void click_Additional_Req_Comment_Box() {
		Additional_Req_Comment_Box.click();
	}
	
	public void add_customer_comments() {
		Additional_Req_Comment_Box.sendKeys("Attaching New Images and Forwarding from Customer");
	}
	
	public void click_Add_Additional_Req_Save_Btn() {
		Add_Additional_Req_Save_Btn.click();
	}
	
	public void click_Files_Browse_Btn() {
		Files_Browse_Btn.click();
	}
	
	public void click_Add_Additional_Req_Close_Btn() {
		Add_Additional_Req_close_Btn.click();
	}
	
	
	public void click_Approval_L2_Upload_Btn(){
		L2_File_Upload.click();
	}
	
	public void click_Add_L2_Rej_Comments(String Rejection_comments) {
		Add_L2_Rej_Comments_Btn.click();
		Add_L2_Rej_Comments_Btn.sendKeys(Rejection_comments);
	}
	
	public String get_ReviewAndL2_popup_text() {
		String Reject_text = L2_Rej_popup_text.getText();
		return Reject_text;
	}
	
	public void click_close_L2_Popup_Btn() {
		close_L2_Popup.click();
	}
}
