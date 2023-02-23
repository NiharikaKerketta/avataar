package UserManagementFlow;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.UserManagementPage;
import ai.avataar.supernovaV3.pages.UserMangNiharika;
import ai.avataar.supernovaV3.util.ListenersClass;
@Listeners(ListenersClass.class)
public class UM_StoreOwner extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	UserManagementPage userManagementPage;
	UserMangNiharika userManagNiharika;
	
	public UM_StoreOwner() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		userManagementPage = new UserManagementPage();
		userManagNiharika = new UserMangNiharika();
	}
	
	@Test(priority= 1)
	public void Select_Tenant_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); //QA store
		commonUtils.mousehover_ToPageTitle("Place Order");
	}
	
	@Test(priority = 2)
	public void Verify_UserManagementStage_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "User Management");
		userManagNiharika.mousehoverTo_AllTeamTitleText("All Teams");
		String PageName = userManagementPage.getAllTeamTitleText();
		System.out.println("Present page is: "+PageName);
	}
	
	@Test(priority = 3)
	public void Verify_TotalTeamMembers_Test() throws InterruptedException {
		Thread.sleep(2000);
		String totalTeam = userManagNiharika.getTotalTeamMemberCountText();
		System.out.println("Total team present : "+totalTeam);
	}
	
	@Test(priority = 4)
	public void Verify_SearchTeam_Test() throws InterruptedException {
		String TeamName = userManagNiharika.getTeamName_Customer();
		commonUtils.SearchBar.sendKeys(TeamName);
		WebElement teamNameDisplay = userManagNiharika.check_TeamName();
		Assert.assertEquals(true, teamNameDisplay.isDisplayed());
		System.out.println("Team name searching for is dislayed : " +teamNameDisplay.isDisplayed());
		Thread.sleep(2000);
	}
	
	@Test(priority = 5)
	public void Verify_editTeam_Test() throws InterruptedException {
		userManagNiharika.click_TeamName("Customer");
		Thread.sleep(2000);
		userManagementPage.Click_TeamInfo_Switcher("Team Members Info");
		Thread.sleep(2000);
	}
	
	@Test(priority = 6)
	public void Verify_add_new_user_Test() throws InterruptedException {
		userManagementPage.addUser("niharika.k@avataar.ai");
	//	Thread.sleep(2000);
		String addedMsg = userManagementPage.getMemberAddedMsg();
		System.out.println("Successfull member added message :"+addedMsg);
		Thread.sleep(2000);
	}
	
	@Test(priority = 7)
	public void Verify_adminPermission_Test() throws InterruptedException {
		WebElement toggelBtn = userManagNiharika.check_Toggel_Btn();
		if(toggelBtn.isEnabled())
		{
			System.out.println("Toggel button is disabled. Return:"+toggelBtn.isEnabled());
		}
		else {
			System.out.println("Toggel button is enabled.Return:"+toggelBtn.isEnabled());
		}
		userManagNiharika.admin_permission_toggle("niharika.k@avataar.ai");
		String AdminStatus = userManagNiharika.getAdminStatusText();
		System.out.println("Admin permission status: "+AdminStatus);
		Thread.sleep(2000);
	}
	
	@Test(priority = 8)
	public void Verify_removeAdminPermission_Test() throws InterruptedException {
		Thread.sleep(2000);
		userManagementPage.admin_permission_toggle("niharika.k@avataar.ai");
		String AdminRemoveStatus = userManagNiharika.getAdminRemoveText();
		System.out.println("Admin permission status: "+AdminRemoveStatus);
		Thread.sleep(2000);
	}
	
	@Test(priority = 9) 
	public void Verify_RemoveUserFromTeam_Test() throws InterruptedException {
		userManagementPage.remove_user("niharika.k@avataar.ai");
		String removeStatus = userManagementPage.getMemberRemoverMsg();
		System.out.println("Successful member removed message :" +removeStatus);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
