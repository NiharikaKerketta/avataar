package Rejection;

import java.awt.AWTException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.Customer_Manager_Rejection_Page;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage1;
import ai.avataar.supernovaV3.pages.ResourceReviewPage;
import ai.avataar.supernovaV3.pages.ThreeD_TeamAssignmentPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)

public class Customer_Rejection extends TestBase {

	
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage1 placeOrderPage;
	ResourceReviewPage resourceReviewPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	ThreeD_TeamAssignmentPage threeD_TeamAssignmentPage;
	Customer_Manager_Rejection_Page customer_manager_rejection;
	Actions act;
	public String ProductID;
	
	public Customer_Rejection() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		placeOrderPage = new PlaceOrderPage1();
		resourceReviewPage = new ResourceReviewPage();
		javaUtils = new JavaUtils();
		orionEnginePage = new OrionEnginePage();
		threeD_TeamAssignmentPage = new ThreeD_TeamAssignmentPage();
		customer_manager_rejection = new Customer_Manager_Rejection_Page();
		act = new Actions(driver);
	}
	
	@Test(priority= 1)
	public void verify_TenantSelection_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Place Order");
	}
	
	@Test(priority= 2)
	public void Verify_Switch_Tenant_and_Stage_To_Review() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Review");
		commonUtils.mousehover_ToPageTitle("Review");
	}
	
	@Test(priority = 3)
	public void verify_GetProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductID();
		System.out.println("Product ID choosen to work on is: "+ProductID);
	}
	
	@Test(priority = 4)
	public void verify_Approval_L2_Stage() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(8000);
		orionEnginePage.click_Feedback_Btn_ReviewStage();
		Thread.sleep(1000);
		orionEnginePage.click_FeedbackRejectBtn();
		Thread.sleep(1000);
		customer_manager_rejection.click_Approval_L2_Upload_Btn();
		javaUtils.pngUpload();
		customer_manager_rejection.click_Add_L2_Rej_Comments("Model Rejected by Store Owner due to improper GLB");
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(1000);
		Assert.assertEquals(customer_manager_rejection.get_ReviewAndL2_popup_text(), "3D Model Rejected","Wrong popup Appeared");
		Thread.sleep(1000);
		customer_manager_rejection.click_close_L2_Popup_Btn();
	}
	
	@Test(priority = 5)
	public void verify_StoreOwner_SignOut_Test() throws InterruptedException {
		commonUtils.SignOut_genericMethod("p");
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@Test(priority = 6)
	public void verify_ManagerSignInFromAvataar_Test() throws InterruptedException{
		loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
	}
	
	@Test(priority = 7)
	public void verify_Tenant_and_stage_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Dashboard");
	}
	
	@Test(priority= 8)
	public void Verify_Switch_Role_To_Modelling_Lead() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Assert.assertEquals(commonUtils.get_current_Role(), "Modelling Lead", "Role selection is wrong");
		Thread.sleep(1000);
	}
	
	@Test(priority = 9)
	public void Verify_Rejected_product() throws InterruptedException{
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.getRejectedWorkStatusAfterQC_Generic("External Rework 1");
	}
	
	@Test(priority = 10)
	public void verify_ManagerSignOut_Test() throws InterruptedException {
		commonUtils.SignOut_genericMethod("3");
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
