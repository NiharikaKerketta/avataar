package MyOrders;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import MMOrderFlow.AcceptanceFinal;
import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.MyOrdersPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage1;
import ai.avataar.supernovaV3.pages.ResourceReviewPage;
import ai.avataar.supernovaV3.pages.ThreeD_TeamAssignmentPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;


@Listeners(ListenersClass.class)
public class MyOrderPrice_Status_MM extends TestBase {
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage1 placeOrderPage;
	ResourceReviewPage resourceReviewPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	ThreeD_TeamAssignmentPage threeD_TeamAssignmentPage;
	MyOrdersPage myOrdersPage;
	MMOrderFlow.AcceptanceFinal acceptanceFinal;
	
	public String ProductID;
	public String PlacedOrderType;
	public String OrderPriceOfProduct_beforeAccepting;
	public String OrderIDOfProduct;
	
	public MyOrderPrice_Status_MM() {
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
		myOrdersPage = new MyOrdersPage();
		acceptanceFinal = new AcceptanceFinal();
	}
	
	@Test(priority= 1)
	public void verify_TenantSelection_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
	}
	
	@Test(priority = 2)
	public void verify_SaveProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductIDByIndex_Generic(1);
		commonUtils.getProductID();
		System.out.println("Product ID selected to verify order price is : "+ProductID);
	}
	
	@Test(priority = 3)
	public void verify_PlaceOrderFromAvataar_Test() throws InterruptedException {
		commonUtils.SelectCheckBoxByIndex_Generic(2);
		placeOrderPage.getBottomOrderText();
		placeOrderPage.click_OrderFromAvataar_Btn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Place order with avataar is successfull");
	}
	
	@Test(priority = 4)
	public void verify_SwitchToMyOrdersPage_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "My Orders");
		commonUtils.mousehover_ToPageTitle("My Orders");
	}
	
	@Test(priority = 5)
	public void verify_OrderType_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		myOrdersPage.getActualOrderType_Generic("Managed marketplace", "3D by Avataar");
	}
	
	@Test(priority = 6)
	public void verify_OrderStatus_Test() throws InterruptedException  {
		((MyOrdersPage) myOrdersPage).getActualOrderStatus_Generic("Store owner places order", "Placed");
	}
	
	@Test(priority = 7)
	public void verify_OrderPrice_beforeAccepting_Test() throws InterruptedException {
		myOrdersPage.getActualPrice_Generic("Store owner places MM order & before accepts by manager ", "Pending");
	}
	
	@Test(priority = 8)
	public void verify_Images_Test() throws InterruptedException {
		myOrdersPage.click_OrderID();
		Thread.sleep(2500);
		assertTrue(myOrdersPage.Image.isDisplayed());
	}
	
	@Test(priority = 9)
	public void verify_StoreOwnerLogoutAndManagerLogin_Test() throws Exception {
		try {
			myOrdersPage.click_Back_Btn_MyOrders();
		} catch (Exception e) {
			System.out.println("Back button not found since did not redirected to L2 page");
		}
		commonUtils.SignOut_genericMethod("p");
		loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
	}
	
	@Test(priority = 10)
	public void verify_ResourceReviewAccept_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Resource Review");
		commonUtils.mousehover_ToPageTitle("Resource Review");
		commonUtils.SearchBar.sendKeys(ProductID);
		System.out.println("product ID entered in resource review search is "+ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		resourceReviewPage.click_AcceptIcon();
		resourceReviewPage.click_ComplexityDropdown();
		resourceReviewPage.select_complexity("1");
		resourceReviewPage.click_CategoryBtn();
		resourceReviewPage.select_category(prop.getProperty("Category")); //change any category in config
		resourceReviewPage.select_subcategory(prop.getProperty("Sub_Category")); //change any sub category in config
		resourceReviewPage.click_SaveBtn_category();
		commonUtils.click_SubmitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Resource Review completed Successfully");
		commonUtils.SignOut_genericMethod("3");
	}
	
	@Test(priority= 11)
	public void verify_StoreOwnerSignInAndStatusCheck_Test() throws InterruptedException {
		loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		verify_TenantSelection_Test();
		commonUtils.mousehover_ToPageTitle("Place Order");
		verify_SwitchToMyOrdersPage_Test();
		commonUtils.SearchBar.sendKeys(ProductID);
		((MyOrdersPage) myOrdersPage).getActualOrderStatus_Generic("Manager accepts order", "In Progress");
	}
	
	@Test(priority= 12)
	public void verify_StoreOwnerPriceCheck_Test() throws InterruptedException {
		myOrdersPage.getActualPrice_Generic("Manager accepts order ", "$99");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}	
	
}
