package BYO3DFlow;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.BYO3DPage;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage1;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)
public class BringYourOwn3D extends TestBase {
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage placeOrderPage;
	PlaceOrderPage1 placeOrderPage1;
	BYO3DPage byo3dPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	
	public String ProductID;
	
	public BringYourOwn3D() {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		placeOrderPage = new PlaceOrderPage();
		byo3dPage = new BYO3DPage();
		javaUtils = new JavaUtils();
		orionEnginePage = new OrionEnginePage();
	}
	
	@Test(priority= 1)
	public void Select_Tenant_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); //QA store
		commonUtils.click_SingleCheckbox_FirstRow();
		}
	
	@Test(priority = 2 )
	public void getProductID_storeProductID_Test() throws InterruptedException {
		commonUtils.getProductID(); 
		commonUtils.storeProductID(); 
		System.out.println("test passed for storing ID");
	}

	@Test(priority = 3 )
	public void verify_PlaceOrder_Test() throws InterruptedException {
		placeOrderPage.getBottomOrderText();
		placeOrderPage.click_Upload3DModels_Btn();
//		Thread.sleep(3000);
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Your order has been Placed");
	}
	
//	@Test(priority = 4)
//	public void Verify_upload3D_Test() throws InterruptedException {
//		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Upload 3D");
//		commonUtils.mousehover_ToPageTitle("Upload 3D");
//	}
//		
		@Test(priority = 4)
		public void Verify_uploadBtn_Test() throws InterruptedException {
//		Thread.sleep(1000);
		ProductID = commonUtils.getProductID();
		commonUtils.SearchBar.click();
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		byo3dPage.click_Upload3DModel_Btn();
	}
	
	@Test(priority = 5)
	public void Verify_UploadGLBFiles_Test() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("3");
		javaUtils.glbFileUpload();
		System.out.println("GLB Uploaded");
	}
	
	@Test(priority = 6)
	public void Verify_UploadInOrion_Test() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		try {
			commonUtils.click_CloseIcon_OnSuccessDialog();
		} catch (Exception e) {
			System.out.println("didn't click on successDialog - mod artist upload stage");
		}
//		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Successfully uploaded GLB");
	}
	
	@Test(priority = 7)
	public void Verify_Publish_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Publish");         // Different Method to switch Modelling QC due to Xpath
		commonUtils.mousehover_ToPageTitle("Publish");
		Thread.sleep(2000);
		for (int i=1; i<22; i++) {
			commonUtils.SearchBar.sendKeys(ProductID);
			Thread.sleep(2000);
			try {
				commonUtils.click_productnameIfClickable();
				System.out.println("successfully completed autoprocessing");
				break;
			} catch (Exception e) {
				System.out.println("not clicked "+i+" time");
			}
			commonUtils.SearchBar.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			Thread.sleep(3000);
		}
		System.out.println("ready to publish");
	}
	
	@Test(priority = 8)
	public void Verify_PublishButton_Test() throws InterruptedException {
//		Thread.sleep(6000);
		byo3dPage.click_BackButton_fromEngine();
//		Thread.sleep(2000);
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		commonUtils.click_PublishBtn();
		Thread.sleep(2000);
		byo3dPage.click_ClosePopupAtPublishStage();
	}
	
	@Test(priority = 9)
	public void Verify_LiveStage_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Live");         // Different Method to switch Modelling QC due to Xpath
		commonUtils.mousehover_ToPageTitle("Live");
		commonUtils.SearchBar.sendKeys(ProductID);
	}
	
	@Test(priority = 10)
	public void verify_StoreOwner_SignOut_Test() throws InterruptedException {
		commonUtils.click_Profile("p");
		Thread.sleep(1000);
		commonUtils.click_SignOut_Btn();
//		Thread.sleep(2000);
		driver.navigate().refresh();
//		Thread.sleep(2000);
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}	
}
