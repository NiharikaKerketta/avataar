package ai.avataar.supernovaV3.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ai.avataar.supernovaV3.base.TestBase;

public class UserManagementPage extends TestBase {
	
	Actions act = new Actions(driver);
	
//-----------UNKale----------
	@FindBy(xpath = "//span[text()='Add Team ']")
	private WebElement UserMgnt_DoneBtn;
	
	@FindBy(xpath = "(//div[@class='textLink'])[1]")
	private WebElement UserMgnt_TeamNameHyperLink;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement SearchBar;				//Generic
	
	@FindBy(xpath = "//div[@xpath='1']")
	private WebElement TeamType;				
	
	@FindBy(xpath = "(//div[@class='TableIcon_icon__ogXlu'])[1]")
	private WebElement Permission;				//Generic
	
	@FindBy(xpath = "//button[text()='Back']")
	private WebElement Back_Btn; 
	
	@FindBy(xpath = "//div[@class='AddEditTeam_email-error__JpAjp']")
	private WebElement already_exists;   //----------UNKale
	
//----------UMB--------
	@FindBy(xpath="//textarea[@name='description']")
	public WebElement Description;
	
	@FindBy(xpath="//span[text()='Save']")
	private WebElement Save_Btn;
	
	@FindBy(xpath="(//input[@type='text'])[1]")
	public WebElement AddEditTeam_searchBar;
	
	@FindBy(xpath="//span[text()='Add']")
	private WebElement AddBtn_withinSearchBar; 
	
	@FindBy(xpath="//span[text()='Login with the email you are invited on ']")
	public WebElement LoginWithInvitedEmaiL_Text;
	
	@FindBy(xpath="//div[text()='Access Denied!']")
	public WebElement AccessDenied_Text; 			
	
	@FindBy(xpath="//input[@class='CustomFormFields_input__+2amW CustomFormFields_small__DlUwZ']") //----------UMB--------
	public WebElement TeamName_Textfield;
	
//----------UNKerketta--------	
	@FindBy(xpath = "//div[text()='Team Updated Successfully']")
	private WebElement ToastMsg;
	
	@FindBy(xpath = "//span[text()='1 Team Member Added !']")
	private WebElement AddedMsg;
	
	@FindBy(xpath = "//span[text()='1 Team Member Removed !']")
	private WebElement RemoveMsg;
	
	@FindBy(xpath = "//div[@class='AddEditTeam_user-name__BSpcr']")
	private WebElement TeamCount;
	
	@FindBy(xpath = "//div[text()='Edit Team']")
	private WebElement EditTeamTitle;
	
	@FindBy(xpath = "//div[text()='All Teams']")
	private WebElement AllTeamTitle;  
	
	@FindBy(xpath = "//span[@class='total-count']")
	private WebElement TotalTeamCount;	
	
	@FindBy(xpath = "//div[text()='Customer']")
	private WebElement TeamName_customer;
	
	@FindBy(xpath = "//div[text()='3d Team Automation']")
	private WebElement TeamName_3D_Team;   //----------UNKerketta--------

	//Initializing page objects:
	public UserManagementPage() { //constructor
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}
	
//-----------UNKale----------
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
		already_exists.getText();       	//----------UNKale
	}
	
//----------UMB--------
	public void click_SaveBtn() {
		Save_Btn.click();
	}

	public void click_AddBtn_withinSearchBar() { 
		try {
			AddBtn_withinSearchBar.click();
		}
		catch(Exception e) {
			System.out.println("User Already Exists");
		}
	}  
	
	public void Click_LoginWithInvitedEmaiL_Text () {   
		LoginWithInvitedEmaiL_Text.click();
	}
	
	public String RandomAlphabet_teamName() {
		String teamName = "Team auto "+ RandomStringUtils.randomAlphabetic(4);
		System.out.println("Random teamname is " +teamName);
		return teamName; 
	}
	
	public String RandomAlphabet_Description() {
		String Description = RandomStringUtils.randomAlphabetic(40);	//----------UMB--------
		System.out.println("Random Description is " +Description);
		return Description; 
	}
	
//----------UNKerketta--------	
	public String getSuccesMsg() {
		return ToastMsg.getText();
	}
	
	public String getMemberAddedMsg() {
		return AddedMsg.getText();
	}
	
	public String getMemberRemoverMsg() {
		return RemoveMsg.getText();
	}
	
	public String getTeamMemberCountText() {
		return TeamCount.getText();
	}
	
	public String getEditTeamTitleText() {
		return EditTeamTitle.getText();
	}
	
	public String getAllTeamTitleText() {     //----------UNKerketta--------
		return AllTeamTitle.getText();
	}

	public String getTotalTeamMemberCountText() {
		return TotalTeamCount.getText();
	}
	
	public String getTeamName_Customer() {
		return TeamName_customer.getText();
	}
	
	public String getTeamName_3DTeam() {
		return TeamName_3D_Team.getText();	
	}
	
	//Generic Methods
	
	public String get_TeamType() {
		String teamType = TeamType.getText();
		return teamType;
	} 
	
	public void Click_TeamName(String TeamName) {
		WebElement TeamName_link = driver.findElement(By.xpath("//div[text()='"+TeamName+"']"));
		TeamName_link.click();
	}
	
	public void Select_TeamType(String teamtype_name) {
		WebElement Select_TeamType_Icon = driver.findElement(By.xpath("//div[text()='"+teamtype_name+"']"));
		Select_TeamType_Icon.click();
	}
	
	public void Click_CancelMailX_withinSearchBar(String CancelMail_Index) {
		WebElement CancelMail_Icon = driver.findElement(By.xpath("(//span[@data-tag-handle='true'])["+CancelMail_Index+"]"));
		CancelMail_Icon.click();
	}
	
	public void Click_TeamInfo_Switcher(String team_info_Switcher) {
		WebElement team = driver.findElement(By.xpath("//span[text()='"+team_info_Switcher+"']"));
		team.click();
	}

	//Added Newly
	public void find_member_on_page(String user_email_id) {
		driver.findElement(By.xpath("//small[normalize-space()='"+user_email_id+"']"));
	}
	
	public void mousehoverTo_AllTeamTitleText(String teamPage_name) {
		WebElement team_page = driver.findElement(By.xpath("//div[text()='"+teamPage_name+"']"));
		team_page.click();
	}
	
	public void click_TeamName(String team_Name) {
		WebElement name = driver.findElement(By.xpath("//div[text()='"+team_Name+"']"));
		name.click();
	}
	
	public void Click_TeamNameFromSearchResult(String newTeam) throws InterruptedException {
		WebElement TeamNameFromSearchResult = driver.findElement(By.xpath("//div[text()='"+newTeam+"']"));
		try {
			if (TeamNameFromSearchResult.isEnabled()) {
				TeamNameFromSearchResult.click();
			} else {
				System.out.println("Created Team is not found in All teams page");
			}
		} catch (Exception e) {
			System.out.println("Created Team is not found in All teams page");
		}
	}
	
	// Added by Nilesh 03/11/22
	public void addUser(String user_mail) throws InterruptedException {
		Thread.sleep(3000);
		AddEditTeam_searchBar.sendKeys(user_mail);
		Thread.sleep(2000);
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
		click_AddBtn_withinSearchBar();
		Thread.sleep(2000);
		System.out.println("User "+user_mail+" Added Successfully...!!");
	}
	
	public void same_member(String user) throws InterruptedException {
		Thread.sleep(3000);
		AddEditTeam_searchBar.sendKeys(user);
		Thread.sleep(2000);
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
		click_AddBtn_withinSearchBar();
		Thread.sleep(2000);
		//getMemberAddedMsg();	
		try {
			Thread.sleep(3000);
			AddEditTeam_searchBar.sendKeys(user);
			Thread.sleep(2000);
			act.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);
			click_AddBtn_withinSearchBar();
			Thread.sleep(2000);
		}
		catch(Exception e){
			get_alert_msg();
		}
	}
	
	public void add_Multiple_User(String user1 ,String user2 ,String user3) throws InterruptedException{
		Thread.sleep(3000);
		addUser(user1);
		Thread.sleep(3000);
		addUser(user2);
		Thread.sleep(3000);
		addUser(user3);
	}
	
	public void remove_user(String emailid) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//small[normalize-space()='"+emailid+"']/../../../"
				+ "button[@class='MuiButtonBase-root MuiButton-root MuiButton-text MemberInfo_"
				+ "delete-icon__0SQ8D MuiButton-textSizeSmall MuiButton-sizeSmall']")).click();
		//driver.navigate().refresh();
		try {
			find_member_on_page(emailid);
		}
		catch(Exception e) {
			System.out.println("User has been Removed");
		}
	}
	
	public void admin_permission_toggle(String user_mail_id) {
		driver.findElement(By.xpath("//small[normalize-space()='"+user_mail_id+"']/../../../div[3]/div/div")).click();
	}
	
	public void add_remove_switch_tenant(String user_email) {
		AddEditTeam_searchBar.click();
		AddEditTeam_searchBar.sendKeys(user_email);
		act.sendKeys(Keys.ENTER);
		click_AddBtn_withinSearchBar();
		getMemberAddedMsg();

		driver.findElement(By.xpath("//small[normalize-space()='"+user_email+"']/../../../"
				+ "button[@class='MuiButtonBase-root MuiButton-root MuiButton-text MemberInfo_"
				+ "delete-icon__0SQ8D MuiButton-textSizeSmall MuiButton-sizeSmall']")).click();
		driver.navigate().refresh();
		try {
			find_member_on_page(user_email);
		}
		catch(Exception e) {
			System.out.println("User has been Removed");
		}
	}
	
	
}
