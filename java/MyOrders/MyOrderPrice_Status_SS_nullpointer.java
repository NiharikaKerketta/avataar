package MyOrders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BYO3DFlow.BringYourOwn3D;
import MMOrderFlow.AcceptanceFinal;
import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.BYO3DPage;
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
public class MyOrderPrice_Status_SS_nullpointer extends TestBase{
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage1 placeOrderPage;
	ResourceReviewPage resourceReviewPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	ThreeD_TeamAssignmentPage threeD_TeamAssignmentPage;
	MyOrdersPage myOrdersPage;
	AcceptanceFinal acceptanceFinal;
	BYO3DPage byo3dPage;
	BringYourOwn3D bringYourOwn3D;
	
	public String ProductID;
	public String PlacedOrderType;
	public String OrderPriceOfProduct_beforeAccepting;
	public String OrderIDOfProduct;
	
	public MyOrderPrice_Status_SS_nullpointer() {
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
		byo3dPage = new BYO3DPage();
		bringYourOwn3D = new BringYourOwn3D();
	}
	
	@Test(priority= 1)
	public void verify_TenantSelection_Test() throws InterruptedException {
		Thread.sleep(2000);
		commonUtils.click_Tenant("Flipzone_Test");
		Thread.sleep(3000);
	}
	
	@Test(priority = 2)
	public void verify_SaveProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductIDByIndex_Generic(7);
		commonUtils.getProductID();
		System.out.println("Product ID selected to verify order price is : "+ProductID);
	}
	
	@Test(priority = 3)
	public void verify_PlaceOrderFromAvataar_Test() throws InterruptedException {
		commonUtils.SelectCheckBoxByIndex_Generic(8);
		placeOrderPage.getBottomOrderText();
		placeOrderPage.click_Upload3DModels_Btn();
		Thread.sleep(2000);
		commonUtils.click_Closeicon_successPopup_placeOrderStage();
		System.out.println("SS order is successfull");
	}
	
	@Test(priority = 4)
	public void verify_SwitchToMyOrdersPage_Test() throws InterruptedException {
		Thread.sleep(2000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage("Fl", "My Orders");
		commonUtils.mousehover_ToPageTitle("My Orders");
		Thread.sleep(2000);
	}
	
	@Test(priority = 5)
	public void verify_OrderType_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(3000);
		myOrdersPage.getActualOrderType_Generic("BYO3D order", "Upload own 3D");
	}
	
	@Test(priority = 6)
	public void verify_OrderStatus_Test() throws InterruptedException  {
		((MyOrdersPage) myOrdersPage).getActualOrderStatus_Generic("Store owner places SS order before upload", "Placed");
	}
	
	@Test(priority = 7)
	public void verify_OrderPrice_Test() throws InterruptedException {
		myOrdersPage.getActualPrice_Generic("Store owner places SS order ", "$0");
	}
	
	@Test(priority = 8)
	public void verify_Images_Test() throws InterruptedException {
		myOrdersPage.click_OrderID();
		Thread.sleep(3000);
		assertTrue(myOrdersPage.Image.isDisplayed());
		try {
			myOrdersPage.click_Back_Btn_MyOrders();
		} catch (Exception e) {
			System.out.println("Back button not found since did not redirected to L2 page");
		}
	}
	
	@Test(priority = 9)
	public void verify_Upload3DModel_Test() throws InterruptedException, AWTException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage("Fl", "Upload 3D");
		Thread.sleep(2000);
		commonUtils.mousehover_ToPageTitle("Upload 3D");
		Thread.sleep(2000);
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(4000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		byo3dPage.click_Upload3DModel_Btn();
		bringYourOwn3D.Verify_UploadGLBFiles_Test();
		bringYourOwn3D.Verify_UploadInOrion_Test();
		bringYourOwn3D.Verify_Publish_Test();
		bringYourOwn3D.Verify_PublishButton_Test();
	}
	
	@Test(priority = 10)
	public void verify_OrderStatusCheck_Test() throws InterruptedException, AWTException {
		verify_SwitchToMyOrdersPage_Test();
		commonUtils.SearchBar.sendKeys(ProductID);
		myOrdersPage.getActualOrderStatus_Generic("Store owner publishes product", "Completed");		
	}
	
	@Test(priority = 11)
	public void verify_PriceCheckAfterPublish_Test() throws InterruptedException, AWTException {
		myOrdersPage.getActualPrice_Generic("After SS order published", "$0");		
	}
	
	
	
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}	
	
	
}
