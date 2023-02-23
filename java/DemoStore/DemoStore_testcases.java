package DemoStore;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.DemoStorePage;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)

public class DemoStore_testcases extends TestBase {
	LoginPage loginPage;
	CommonUtils commonUtils;
	DemoStorePage demoStorePage;
	
	public DemoStore_testcases() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		demoStorePage = new DemoStorePage();
	}
		
	@Test(priority = 1)
	public void verify_TenantSelectionAndSwitchToDemostore_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Place Order");
		try {
			commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Demo Store");
		} catch (Exception e) {
			System.out.println("page title not applicable");
		}}
	
	@Test(priority = 2)
	public void verify_WelcomePage_Test() {
		try {
			if (demoStorePage.Continue_Btn_WelcomePage.isEnabled() ) {
				demoStorePage.click_Continue_Btn_WelcomePage();
			} else {
				System.out.println("Welcome note is not displayed along with Continue button");
			}
		} catch (Exception e) {
			System.out.println("Welcome note is not displayed along with Continue button");
		}
	}
	
	@Test(priority = 3 )
	public void verify_PlaceOrderPage_Test() throws InterruptedException {
		demoStorePage.Select_checkbox(2);
		Thread.sleep(500);
		demoStorePage.click_OrderFromAvataar_Btn();
		Thread.sleep(500);
		demoStorePage.select_Checkbox_InPublishStage();
		Thread.sleep(500);
		demoStorePage.click_Publish_Btn();
		Thread.sleep(500);
	}
	
	@Test(enabled = false)
	public void verify_CustomerExperienceTest() throws InterruptedException {
//		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
//		int size = driver.findElements(By.tagName("iframe")).size();
//		System.out.println(size);
//		driver.switchTo().frame(1);
//		Thread.sleep(2000);
//		demoStorePage.click_Dimension_Icon();
//		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS); 
//		WebDriverWait wait = new WebDriverWait(driver,(50));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='Button_button__2Aw9C'])[1]"))).click();
//		Thread.sleep(10000);
		demoStorePage.click_InYourRoom_Toggle();
//		demoStorePage.click_ViewIn3D_Toggle();
	}
	
	@Test(priority = 4)
	public void verify_CloseExperience_Test() throws InterruptedException {
		demoStorePage.click_Close_Icon_ExperiencePage();
		Thread.sleep(500);
	}
	
	@Test(priority = 5)
	public void verify_Place3DOrder_Test() throws InterruptedException {
		demoStorePage.click_Place3DOrder_Btn();	
		Thread.sleep(1000);
	}
	
	@Test(priority = 6)
	public void verify_StoreOwner_SignOut_Test() throws InterruptedException {
		commonUtils.SignOut_genericMethod("p");
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}	
}
