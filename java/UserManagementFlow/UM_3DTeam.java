package UserManagementFlow;

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
public class UM_3DTeam extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	UserManagementPage userManagementPage;
	UserMangNiharika userManagNiharika;
	
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
		String TeamName = userManagNiharika.getTeamName_3DTeam();
		commonUtils.SearchBar.sendKeys(TeamName);
		System.out.println("Team name searching for: "+TeamName);
		userManagNiharika.click_TeamName(TeamName);
		Thread.sleep(2000);
	}
	
	@Test(priority = 5)
	public void Verify_add_new_user() throws InterruptedException {
		userManagementPage.Click_TeamInfo_Switcher("Team Leads Info");
		userManagementPage.addUser("nilesh.ashok@avataar.ai");
		userManagementPage.Click_TeamInfo_Switcher("Team Members Info");
	}
	
	@Test(priority = 6)
	public void Verify_remove_user() throws InterruptedException {
		userManagementPage.Click_TeamInfo_Switcher("Team Leads Info");
		userManagementPage.remove_user("nilesh.ashok@avataar.ai");
		userManagementPage.Click_TeamInfo_Switcher("Team Members Info");
	}
	
	@Test(priority = 7)
	public void Verify_adding_same_user() throws InterruptedException {
		userManagementPage.Click_TeamInfo_Switcher("Team Leads Info");
		userManagementPage.same_member("poojitha.Konduru@avataar.ai");
		userManagementPage.Click_TeamInfo_Switcher("Team Members Info");
	}
	
	@Test(priority = 8)
	public void Verify_adding_multiple_user() throws InterruptedException {
		userManagementPage.Click_TeamInfo_Switcher("Team Leads Info");
		userManagementPage.add_Multiple_User("kishan.Yadav@avataar.ai", "rohan.c@avataar.ai", "nilesh.ashok@avataar.ai");
		userManagementPage.Click_TeamInfo_Switcher("Team Members Info");
	}
	
	@Test(priority = 9, enabled=false)
	public void Verify_add_remove_switch_tenant() {
		
	}
	
	@Test(priority = 10)
	public void Verify_navigation_Test() throws InterruptedException {
		userManagementPage.click_UserMgnt_Back_Btn();
		Verify_SearchTeam_Test();
		userManagementPage.Click_TeamInfo_Switcher("Team Leads Info");
		try {
		userManagementPage.find_member_on_page("kishan.Yadav@avataar.ai");}
		catch (Exception e) {
			System.out.println("User Not Found");
		}
		userManagementPage.click_UserMgnt_Back_Btn();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
