package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ai.avataar.supernovaV3.base.TestBase;

	public class UserMangNiharika extends TestBase{
		
		
		@FindBy(xpath = "//input[@placeholder='Search']")			//can also take from common utils
		private WebElement secarchTeamBox;
		
		@FindBy(xpath = "//div[text()='Team Updated Successfully']")
		private WebElement toastMsg;
		
		@FindBy(xpath = "//span[text()='1 Team Member Added !']")
		private WebElement addedMsg;
		
		@FindBy(xpath = "//span[text()='1 Team Member Removed !']")
		private WebElement removeMsg;
		
		@FindBy(xpath = "//div[@class='AddEditTeam_user-name__BSpcr']")
		private WebElement teamCount;
		
		@FindBy(xpath = "//div[text()='Edit Team']")
		private WebElement editTeamTitle;
		
		@FindBy(xpath = "//div[text()='All Teams']")
		private WebElement allTeamTitle;
		
		//-----added on 28-10-2022
		@FindBy(xpath = "//span[@class='total-count']")
		private WebElement TotalTeamCount;	
		
		@FindBy(xpath = "//div[text()='Customer']")
		private WebElement TeamName_customer;
		
		@FindBy(xpath = "//div[text()='3d Team Automation']")
		private WebElement TeamName_3D_Team;
		
		//added on 9-11-22
		@FindBy(xpath = "//span[text()='Admin Permission Given']")
		private WebElement adminMsg;
		
		@FindBy(xpath = "//span[text()='Admin Permission Revoked']")
		private WebElement adminRemoveMsg;
		
		@FindBy(xpath = "//div[@class='textLink']")
		private WebElement TeamNameDisplay;
		
		@FindBy(xpath="//input[@type='text']")
		private WebElement addOneUser;
		
		@FindBy(xpath = "(//div[@class='Toggle_toggleWrapper__wnWQp Toggle_grey__tX5eL'])[1]")
		private WebElement toggleBtn;
		//------------
		
		//Initializing page objects:
		public UserMangNiharika() { //constructor
			PageFactory.initElements(driver, this);//this means current class variables/objects
		}

		public void Select_TeamInfo_Switcher(String team_info) {
			WebElement team = driver.findElement(By.xpath("//span[text()='"+team_info+"']"));
			team.click();
		}
		
		public void click_search_team() {
			secarchTeamBox.click();
		}
		
		public String getSuccesMsg() {
			return toastMsg.getText();
		}
		
		public String getMemberAddedMsg() {
			return addedMsg.getText();
		}
		
		public String getMemberRemoverMsg() {
			return removeMsg.getText();
		}
		
		public String getTeamMemberCountText() {
			return teamCount.getText();
		}
		
		public String getEditTeamTitleText() {
			return editTeamTitle.getText();
		}
		
		public String getAllTeamTitleText() {
			return allTeamTitle.getText();
		}
		
		//added on 28-10-2022
		
		public void mousehoverTo_AllTeamTitleText(String teamPage_name) {
			WebElement team_page = driver.findElement(By.xpath("//div[text()='"+teamPage_name+"']"));
			team_page.click();
		}
		
		public String getTotalTeamMemberCountText() {
			return TotalTeamCount.getText();
		}
		
		public void click_TeamName(String team_Name) {
			WebElement name = driver.findElement(By.xpath("//div[text()='"+team_Name+"']"));
			name.click();
		}
		
		public String getTeamName_Customer() {
			return TeamName_customer.getText();
			
		}
		
		public String getTeamName_3DTeam() {
			return TeamName_3D_Team.getText();
			
		}
		
		//added on 9-11-22
		public String getAdminStatusText() {
			return adminMsg.getText();
		}
		
		public String getAdminRemoveText() {
			return adminRemoveMsg.getText();
		}
		
		public WebElement check_TeamName() {
		return TeamNameDisplay;
		}
		
		public void click_AddMembers() {
			addOneUser.click();
		}
		
		public WebElement check_Toggel_Btn() {
			return toggleBtn;
		}
		
		//copied from nilesh
		public void admin_permission_toggle(String user_mail_id) {
			driver.findElement(By.xpath("//small[normalize-space()='"+user_mail_id+"']/../../../div[3]/div/div")).click();
		}
		
	}
