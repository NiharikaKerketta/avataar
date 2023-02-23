package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ai.avataar.supernovaV3.base.TestBase;

public class UserManagementPageNilesh extends TestBase{
	
	@FindBy(xpath = "//span[text()='Add Team ']")
	private WebElement UserMgnt_DoneBtn;
	
	@FindBy(xpath = "(//div[@class='textLink'])[1]")
	private WebElement UserMgnt_TeamNameHyperLink;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement SearchBar;				//Generic
	
	@FindBy(xpath = "//div[@xpath='1']")
	private WebElement TeamType;				
	
	@FindBy(xpath = "(//div[@class='TableIcon_icon__ogXlu'])[1]")
	private WebElement permission;				//Generic
	
	@FindBy(xpath = "//button[text()='Back']")
	private WebElement Back_Btn;
	
	@FindBy(xpath = "//div[@class='AddEditTeam_email-error__JpAjp']")
	private WebElement already_exists;

	//Initializing page objects:
	public UserManagementPageNilesh() { //constructor
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}

	public void click_AddTeam_Btn() {
		UserMgnt_DoneBtn.click();
	}
	
	public void click_TeamName() {
		UserMgnt_TeamNameHyperLink.click();
	}
	
	public void click_SearchBar() {
		SearchBar.click();
	}
	
	public void click_UserMgnt_Back_Btn() {
		Back_Btn.click();
	}
	
	public void get_alert_msg() {
		already_exists.getText();
	}
	
	//Generic Methods
	
	public String get_TeamType() {
		String teamType = TeamType.getText();
		return teamType;
	}
	
	public void remove_user(String Name_Of_User) {
		driver.findElement(By.xpath("//div[normalize-space()='"+Name_Of_User+"']"));
		
	}
	
	//Added Newly
	public void find_member_on_page(String user_email_id) {
		driver.findElement(By.xpath("//small[normalize-space()='"+user_email_id+"']"));
	}
}
