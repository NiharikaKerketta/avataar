package UserManagementFlow;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage1;
import ai.avataar.supernovaV3.pages.ResourceReviewPage;
import ai.avataar.supernovaV3.pages.ThreeD_TeamAssignmentPage;
import ai.avataar.supernovaV3.pages.UserManagementPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;
@Listeners(ListenersClass.class)
public class UM_CreatingTeam extends TestBase{
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage1 placeOrderPage;
	ResourceReviewPage resourceReviewPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	ThreeD_TeamAssignmentPage threeD_TeamAssignmentPage;
	UserManagementPage userManagementPage;
	
	public String ProductID;
	public String newTeamName;
	
	public UM_CreatingTeam() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		commonUtils = new CommonUtils();
		placeOrderPage = new PlaceOrderPage1();
		resourceReviewPage = new ResourceReviewPage();
		javaUtils = new JavaUtils();
		orionEnginePage = new OrionEnginePage();
		threeD_TeamAssignmentPage = new ThreeD_TeamAssignmentPage();
		userManagementPage = new UserManagementPage();
	}
	
	@Test(priority= 1)
	public void verify_AccessDeniedPage_Test() throws InterruptedException {
		loginPage = new LoginPage();
		try {
			commonUtils = loginPage.login(prop.getProperty("new_user"), prop.getProperty("QA_pwd"));
		} catch (Exception e) {
			System.out.println("user is not invited");
		}
		userManagementPage.AccessDenied_Text.isDisplayed();
		System.out.println(userManagementPage.AccessDenied_Text.getText());
	}
	
	@Test(priority= 2)
	public void verify_LoginWithInvitedEmaiL_Link_Test() throws InterruptedException {
		userManagementPage.Click_LoginWithInvitedEmaiL_Text();
		Thread.sleep(3000);
		loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
	}
	
	@Test(priority= 3)
	public void verify_TenantSelection_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Place Order"); 
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "User Management");
	}
	
	@Test(priority= 4)
	public void verify_AddTeamInfo_Test() throws InterruptedException {
		userManagementPage.click_AddTeam_Btn();
		userManagementPage.Select_TeamType("3D Team");
		newTeamName = userManagementPage.RandomAlphabet_teamName();
		userManagementPage.TeamName_Textfield.sendKeys(newTeamName);
		userManagementPage.Description.sendKeys(userManagementPage.RandomAlphabet_Description());
		Thread.sleep(2000);
	}
	
	@Test(priority= 5)
	public void verify_AddTeamLeads_Test() throws InterruptedException {
		userManagementPage.Click_TeamInfo_Switcher("Add Team Leads");
		Thread.sleep(2000);
		userManagementPage.AddEditTeam_searchBar.sendKeys(prop.getProperty("TeamLead_Manju"),Keys.ENTER );
		Thread.sleep(2000);
	}
	
	@Test(priority= 6)
	public void verify_AddTeamMemberAndSave_Test() throws InterruptedException {
		userManagementPage.Click_TeamInfo_Switcher("Add Team Members");
		Thread.sleep(2000);
		userManagementPage.AddEditTeam_searchBar.sendKeys(prop.getProperty("TeamMember_Manju"),Keys.ENTER );
		Thread.sleep(2000);
		userManagementPage.click_SaveBtn();
		Thread.sleep(3000);
	}
	
	@Test(priority= 7)
	public void verify_newTeamInAllTeams_Test() throws InterruptedException {
		userManagementPage.SearchBar.sendKeys(newTeamName);
		Thread.sleep(2000);
		userManagementPage.Click_TeamNameFromSearchResult(newTeamName);
		Thread.sleep(3000);
	}
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
